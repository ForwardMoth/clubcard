package com.example.clubcard.service.impl;

import com.example.clubcard.domain.dto.page.PageDto;
import com.example.clubcard.domain.dto.privilege.PrivilegeIdRequest;
import com.example.clubcard.domain.dto.user.*;
import com.example.clubcard.domain.entity.Privilege;
import com.example.clubcard.domain.entity.User;
import com.example.clubcard.domain.enums.PrivilegeEnum;
import com.example.clubcard.domain.enums.RoleEnum;
import com.example.clubcard.domain.mapper.UserMapper;
import com.example.clubcard.exception.message.UserErrorMessage;
import com.example.clubcard.exception.type.BadRequestException;
import com.example.clubcard.exception.type.NotFoundException;
import com.example.clubcard.repository.RoleRepository;
import com.example.clubcard.repository.UserRepository;
import com.example.clubcard.repository.criteria.UserCriteriaRepository;
import com.example.clubcard.service.PrivilegeService;
import com.example.clubcard.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeService privilegeService;
    private final UserMapper userMapper;
    private final UserCriteriaRepository userCriteriaRepository;

    public User create(User user) {
        user.setIsBlocked(false);
        user.setMoney(0);
        user.setUUID();
        user.setRole(roleRepository.findByName(RoleEnum.USER.name()).orElseThrow(
                () -> new NotFoundException(UserErrorMessage.ROLE_NOT_FOUND.getName())
        ));
        user.setPrivilege(privilegeService.getByName(PrivilegeEnum.STANDARD.getName()));
        return save(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with '%s' isn't found", email)));
    }

    public Boolean isExisted(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByEmail(username);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), user.getAuthorities()
        );
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(UserErrorMessage.USER_IS_NOT_FOUND.getName())
        );
    }

    public UserProfileResponse getProfile(Long id) {
        User user = findById(id);

        if (user.getIsBlocked()) {
            return new UserProfileResponse();
        }

        return userMapper.toProfileResponse(user);
    }

    public UserBalanceResponse getBalance(Long id) {
        User user = findById(id);

        if (user.getIsBlocked()) {
            return new UserBalanceResponse();
        }

        return userMapper.toBalanceResponse(user);
    }

    public UserStatusResponse getStatus(Long id) {
        User user = findById(id);

        if (user.getIsBlocked()) {
            return new UserStatusResponse(null, true, null);
        }

        return userMapper.toStatusResponse(user);
    }

    public UserResponse getUser(Long id) {
        return userMapper.toDto(findById(id));
    }

    public UserResponse updateProfile(Long id, UserUpdateRequest request) {
        User user = findById(id);

        isUpdateBlocked(user);

        userMapper.updateUserFromDto(request, user);
        save(user);
        return userMapper.toDto(user);
    }

    public UserStatusResponse updateStatus(Long id) {
        User user = findById(id);
        user.setIsBlocked(!user.getIsBlocked());
        save(user);
        return userMapper.toStatusResponse(user);
    }

    public UserResponse updatePrivilege(Long id, PrivilegeIdRequest request) {
        Privilege privilege = privilegeService.findById(request.getPrivilegeId());
        User user = findById(id);

        isUpdateBlocked(user);

        Integer privilegePrice = privilege.getPrice();
        Integer userBalance = user.getMoney();
        if (userBalance < privilegePrice) {
            throw new BadRequestException(UserErrorMessage.NOT_ENOUGH_MONEY.getName());
        }

        user.setMoney(userBalance - privilegePrice);
        user.setPrivilege(privilege);
        save(user);
        return userMapper.toDto(user);
    }

    public void deleteUser(Long id) {
        userRepository.delete(findById(id));
    }

    @Override
    public Page<UserResponse> getAllUsers(PageDto pageDto,
                                          UserFilterRequest userFilterRequest) {
        return userCriteriaRepository.findAllWithFilters(pageDto, userFilterRequest).map(userMapper::toDto);
    }

    @Override
    public UserQrCodeResponse getQrCode(Long id) {
        User user = findById(id);

        if (user.getIsBlocked()) {
            return new UserQrCodeResponse();
        }

        return userMapper.toQrCodeResponse(user);
    }

    @Override
    public UserResponse getUserByQrCode(String uuid) {
        User user = findByUuid(uuid);

        if (user.getIsBlocked()) {
            return new UserResponse();
        }

        return userMapper.toDto(user);
    }

    @Override
    public UserBalanceResponse updateBalance(Long id, UserBalanceRequest request) {
        User user = findById(id);

        isUpdateBlocked(user);

        Integer money = request.getMoney();
        Integer userBalance = user.getMoney();

        if (userBalance + money < 0) {
            throw new BadRequestException(UserErrorMessage.BALANCE_CANT_BE_NEGATIVE.getName());
        }

        user.setMoney(userBalance + money);
        return userMapper.toBalanceResponse(userRepository.save(user));
    }

    private User findByUuid(String uuid) {
        return userRepository.findByUuid(uuid).orElseThrow(
                () -> new NotFoundException(UserErrorMessage.USER_IS_NOT_FOUND.getName())
        );
    }

    private void isUpdateBlocked(User user) {
        if (user.getIsBlocked()) {
            throw new BadRequestException(UserErrorMessage.CANT_UPDATE_FOR_BLOCKED.getName());
        }
    }
}


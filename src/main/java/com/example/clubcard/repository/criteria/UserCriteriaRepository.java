package com.example.clubcard.repository.criteria;

import com.example.clubcard.domain.dto.page.PageDto;
import com.example.clubcard.domain.dto.user.UserFilterRequest;
import com.example.clubcard.domain.entity.User;
import com.example.clubcard.exception.message.AppErrorMessage;
import com.example.clubcard.exception.type.BadRequestException;
import com.example.clubcard.service.PrivilegeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class UserCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;
    private final PrivilegeService privilegeService;

    public UserCriteriaRepository(EntityManager entityManager, PrivilegeService privilegeService) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
        this.privilegeService = privilegeService;
    }

    public Page<User> findAllWithFilters(PageDto pageDto,
                                         UserFilterRequest userFilterRequest) {
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Predicate predicate = getPredicate(userFilterRequest, userRoot);
        criteriaQuery.where(predicate);
        setOrder(pageDto, criteriaQuery, userRoot);

        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(pageDto.getOffset() * pageDto.getLimit());
        typedQuery.setMaxResults(pageDto.getLimit());

        Pageable pageable = getPageable(pageDto);
        long userCount = getUserCount();

        return new PageImpl<>(typedQuery.getResultList(), pageable, userCount);
    }

    private void setOrder(PageDto pageDto, CriteriaQuery<User> criteriaQuery, Root<User> userRoot) {
        try {
            if (pageDto.getSortDirection().equals(Sort.Direction.ASC)) {
                criteriaQuery.orderBy(criteriaBuilder.asc(userRoot.get(pageDto.getSortBy())));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(userRoot.get(pageDto.getSortBy())));
            }
        } catch (Exception e) {
            throw new BadRequestException(AppErrorMessage.INCORRECT_ATTRIBUTE_NAME.getName());
        }

    }

    private long getUserCount() {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<User> countRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(criteriaBuilder.count(countRoot));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    private Pageable getPageable(PageDto pageDto) {
        Sort sort = Sort.by(pageDto.getSortDirection(), pageDto.getSortBy());
        return PageRequest.of(pageDto.getOffset(), pageDto.getLimit());
    }

    private Predicate getPredicate(UserFilterRequest userFilterRequest,
                                   Root<User> userRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(userFilterRequest.getLastName())) {
            predicates.add(
                    criteriaBuilder.like(userRoot.get("lastName"),
                            "%" + userFilterRequest.getLastName() + "%")
            );
        }

        if (Objects.nonNull(userFilterRequest.getIsBlocked())) {
            predicates.add(
                    criteriaBuilder.equal(userRoot.get("isBlocked"),
                            userFilterRequest.getIsBlocked())
            );
        }

        if (Objects.nonNull(userFilterRequest.getPrivilegeId())) {
            predicates.add(
                    criteriaBuilder.equal(userRoot.get("privilege"),
                            privilegeService.findById(userFilterRequest.getPrivilegeId()))
            );
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

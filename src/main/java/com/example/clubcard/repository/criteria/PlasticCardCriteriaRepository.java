package com.example.clubcard.repository.criteria;

import com.example.clubcard.domain.dto.page.PageDto;
import com.example.clubcard.domain.dto.plastic_card.PlasticCardFilterRequest;
import com.example.clubcard.domain.entity.PlasticCard;
import com.example.clubcard.service.CardTypeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class PlasticCardCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;
    private final CardTypeService cardTypeService;

    public PlasticCardCriteriaRepository(EntityManager entityManager, CardTypeService cardTypeService) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
        this.cardTypeService = cardTypeService;
    }

    public Page<PlasticCard> findAllWithFilters(PageDto pageDto,
                                                PlasticCardFilterRequest plasticCardFilterRequest) {
        CriteriaQuery<PlasticCard> criteriaQuery = criteriaBuilder.createQuery(PlasticCard.class);
        Root<PlasticCard> plasticCardRoot = criteriaQuery.from(PlasticCard.class);
        Predicate predicate = getPredicate(plasticCardFilterRequest, plasticCardRoot);
        criteriaQuery.where(predicate);

        TypedQuery<PlasticCard> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(pageDto.getOffset() * pageDto.getLimit());
        typedQuery.setMaxResults(pageDto.getLimit());

        Pageable pageable = getPageable(pageDto);
        long plasticCardCount = getPlasticCardCount();

        return new PageImpl<>(typedQuery.getResultList(), pageable, plasticCardCount);
    }

    private long getPlasticCardCount() {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<PlasticCard> countRoot = criteriaQuery.from(PlasticCard.class);
        criteriaQuery.select(criteriaBuilder.count(countRoot));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    private Pageable getPageable(PageDto pageDto) {
        return PageRequest.of(pageDto.getOffset(), pageDto.getLimit());
    }

    private Predicate getPredicate(PlasticCardFilterRequest plasticCardFilterRequest,
                                   Root<PlasticCard> plasticCardRoot) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(plasticCardFilterRequest.getStatus())){
            predicates.add(
                    criteriaBuilder.equal(plasticCardRoot.get("status"),
                            plasticCardFilterRequest.getStatus())
            );
        }

        if (Objects.nonNull(plasticCardFilterRequest.getCreatedAt())){
            predicates.add(
                    criteriaBuilder.equal(plasticCardRoot.get("createdAt"),
                            plasticCardFilterRequest.getCreatedAt())
            );
        }

        if (Objects.nonNull(plasticCardFilterRequest.getCardTypeId())){
            predicates.add(
                    criteriaBuilder.equal(plasticCardRoot.get("cardType"),
                            cardTypeService.findById(plasticCardFilterRequest.getCardTypeId()))
            );
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

package com.example.Swipe.Admin.specification;

import com.example.Swipe.Admin.entity.LCD;
import com.example.Swipe.Admin.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

@Builder
public class LcdSpecification implements Specification<LCD> {
    private String keyWord;

    @Override
    public Predicate toPredicate(Root<LCD> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        query.distinct(true);

        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.or(
                        criteriaBuilder.like(root.get("name"), "%" + keyWord + "%")
//                        criteriaBuilder.like(root.get("surname"), "%" + keyWord + "%"),
//                        criteriaBuilder.like(root.get("mail"), "%" + keyWord + "%")
//                    criteriaBuilder.equal(root.get("id"), userSearchingDto.getName())

                )
        );
        query.orderBy(criteriaBuilder.asc(root.get("name")));
        return predicate;


    }
}

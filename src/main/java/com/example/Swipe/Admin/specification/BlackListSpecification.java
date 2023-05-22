package com.example.Swipe.Admin.specification;

import com.example.Swipe.Admin.entity.User;
import jakarta.persistence.criteria.*;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

@Builder
public class BlackListSpecification implements Specification<User> {
    private String keyWord;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        query.distinct(true);

        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.isTrue(root.get("blackList")),
                criteriaBuilder.or(
                        criteriaBuilder.like(root.get("name"), "%" + keyWord + "%"),
                        criteriaBuilder.like(root.get("surname"), "%" + keyWord + "%")
//                        criteriaBuilder.like(root.get("mail"), "%" + keyWord + "%")
//                    criteriaBuilder.equal(root.get("id"), userSearchingDto.getName())

                )
        );
        query.orderBy(criteriaBuilder.asc(root.get("surname")));
        return predicate;


    }
}

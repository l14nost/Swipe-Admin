package com.example.Swipe.Admin.specification;

import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.enums.TypeUser;
import javax.persistence.criteria.*;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.Specification;

@Builder
@EqualsAndHashCode
public class UserSpecification implements Specification<User> {
    private String keyWord;
    private TypeUser typeUser;

    private String sort;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        query.distinct(true);
        Predicate predicate;
        if (keyWord!=null) {
             predicate = criteriaBuilder.and(
                    criteriaBuilder.isFalse(root.get("blackList")),
                    criteriaBuilder.equal(root.get("typeUser"), typeUser),
                    criteriaBuilder.or(
                            criteriaBuilder.like(root.get("name"), "%" + keyWord + "%"),
                            criteriaBuilder.like(root.get("surname"), "%" + keyWord + "%")
                    )
            );
        }
        else {
            predicate = criteriaBuilder.and(
                    criteriaBuilder.isFalse(root.get("blackList")),
                    criteriaBuilder.equal(root.get("typeUser"), typeUser));
        }
        query.orderBy(criteriaBuilder.asc(root.get(sort)));

        return predicate;


    }
}

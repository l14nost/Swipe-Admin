package com.example.Swipe.Admin.specification;

import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.enums.TypeUser;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.Specification;

@Builder
@EqualsAndHashCode
public class UserSpecification implements Specification<User> {
    private String keyWord;
    private TypeUser typeUser;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        query.distinct(true);

        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.isFalse(root.get("blackList")),
                criteriaBuilder.equal(root.get("typeUser"),typeUser),
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

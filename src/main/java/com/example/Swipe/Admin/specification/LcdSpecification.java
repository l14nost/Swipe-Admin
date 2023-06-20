package com.example.Swipe.Admin.specification;

import com.example.Swipe.Admin.entity.LCD;
import com.example.Swipe.Admin.entity.User;
import javax.persistence.criteria.*;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.Specification;

@Builder
@EqualsAndHashCode
public class LcdSpecification implements Specification<LCD> {
    private String keyWord;

    private String sort;

    @Override
    public Predicate toPredicate(Root<LCD> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        query.distinct(true);
        Predicate predicate = null;
        if (keyWord!=null) {
             predicate = criteriaBuilder.and(
                    criteriaBuilder.or(
                            criteriaBuilder.like(root.get("name"), "%" + keyWord + "%"),
                            criteriaBuilder.like(root.get("address"), "%" + keyWord + "%")

//                        criteriaBuilder.like(root.get("surname"), "%" + keyWord + "%"),
//                        criteriaBuilder.like(root.get("mail"), "%" + keyWord + "%")
//                    criteriaBuilder.equal(root.get("id"), userSearchingDto.getName())

                    )
            );
        }

        query.orderBy(criteriaBuilder.asc(root.get(sort)));
        return predicate;


    }
}

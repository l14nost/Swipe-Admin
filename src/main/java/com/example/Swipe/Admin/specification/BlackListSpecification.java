package com.example.Swipe.Admin.specification;

import com.example.Swipe.Admin.entity.User;
import javax.persistence.criteria.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.Specification;

@Builder
@EqualsAndHashCode
public class BlackListSpecification implements Specification<User> {
    private String keyWord;
    private String sortedBy;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        query.distinct(true);
        if (!keyWord.equals("null")) {
            Predicate predicate = criteriaBuilder.and(
                    criteriaBuilder.isTrue(root.get("blackList")),
                    criteriaBuilder.or(
                            criteriaBuilder.like(root.get("name"), "%" + keyWord + "%"),
                            criteriaBuilder.like(root.get("surname"), "%" + keyWord + "%")
//                        criteriaBuilder.like(root.get("mail"), "%" + keyWord + "%")
//                    criteriaBuilder.equal(root.get("id"), userSearchingDto.getName())

                    )
            );
            query.orderBy(criteriaBuilder.asc(root.get(sortedBy)));
            return predicate;
        }
        else {
            Predicate predicate =
                    criteriaBuilder.isTrue(root.get("blackList"));

            query.orderBy(criteriaBuilder.asc(root.get(sortedBy)));
            return predicate;
        }


    }
}

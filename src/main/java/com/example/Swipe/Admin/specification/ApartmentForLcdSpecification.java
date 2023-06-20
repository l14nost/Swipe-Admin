package com.example.Swipe.Admin.specification;

import com.example.Swipe.Admin.entity.Apartment;
import javax.persistence.criteria.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.Specification;

@Builder
@EqualsAndHashCode
public class ApartmentForLcdSpecification implements Specification<Apartment> {
    private int keyWord;
    private String sort;

    private boolean isFrame;

    @Override
    public Predicate toPredicate(Root<Apartment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        query.distinct(true);
        Predicate predicate = null;
        if (!isFrame) {
            predicate = criteriaBuilder.isNull(root.get("frame"));
            if (keyWord != 0) {
                predicate = criteriaBuilder.and(
                        criteriaBuilder.isNull(root.get("frame")),
                        criteriaBuilder.or(
                                criteriaBuilder.equal(root.get("number"), keyWord)
//                        criteriaBuilder.like(root.get("surname"), "%" + keyWord + "%"),
//                        criteriaBuilder.like(root.get("mail"), "%" + keyWord + "%")
//                    criteriaBuilder.equal(root.get("id"), userSearchingDto.getName())

                        )
                );
            }
            query.orderBy(criteriaBuilder.asc(root.get(sort)));
        }
        return predicate;


    }
}

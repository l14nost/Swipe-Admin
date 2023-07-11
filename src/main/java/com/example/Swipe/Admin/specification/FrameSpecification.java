package com.example.Swipe.Admin.specification;

import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.Frame;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Builder
@EqualsAndHashCode
public class FrameSpecification implements Specification<Frame> {
    private int keyWord;
    private String sort;
    private int order;


    @Override
    public Predicate toPredicate(Root<Frame> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        query.distinct(true);
        Predicate predicate = null;
            if (keyWord != 0) {
                predicate = criteriaBuilder.and(
                        criteriaBuilder.or(
                                criteriaBuilder.equal(root.get("num"), keyWord)

                        )
                );
            }
            if (order == 1) {
                query.orderBy(criteriaBuilder.asc(root.get(sort)));
            }
            else if (order==2){
                query.orderBy(criteriaBuilder.desc(root.get(sort)));
            }
        return predicate;


    }
}

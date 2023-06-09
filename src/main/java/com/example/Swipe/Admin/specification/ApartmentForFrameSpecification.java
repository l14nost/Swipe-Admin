package com.example.Swipe.Admin.specification;

import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.Frame;
import javax.persistence.criteria.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.Specification;

@Builder
@EqualsAndHashCode
public class ApartmentForFrameSpecification implements Specification<Apartment> {
    private int keyWord;
    private Frame frame;
    private String sort;
    private int order;

    @Override
    public Predicate toPredicate(Root<Apartment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        query.distinct(true);

        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(root.get("frame"),frame));
        if(keyWord!=0){
               predicate = criteriaBuilder.and(
                       criteriaBuilder.equal(root.get("frame"),frame),
                       criteriaBuilder.equal(root.get("number"),keyWord)
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

package com.example.Swipe.Admin.specification;

import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.Frame;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

@Builder
public class ApartmentForFrameSpecification implements Specification<Apartment> {
    private int keyWord;
    private Frame frame;

    @Override
    public Predicate toPredicate(Root<Apartment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        query.distinct(true);

        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(root.get("frame"),frame),
                criteriaBuilder.or(
                        criteriaBuilder.equal(root.get("number"),keyWord)
//                        criteriaBuilder.like(root.get("surname"), "%" + keyWord + "%"),
//                        criteriaBuilder.like(root.get("mail"), "%" + keyWord + "%")
//                    criteriaBuilder.equal(root.get("id"), userSearchingDto.getName())

                )
        );
        query.orderBy(criteriaBuilder.asc(root.get("number")));
        return predicate;


    }
}

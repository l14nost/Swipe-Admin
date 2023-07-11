package com.example.Swipe.Admin.specification;

import com.example.Swipe.Admin.entity.News;
import com.example.Swipe.Admin.entity.User;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Builder
@EqualsAndHashCode
public class NewsSpecification implements Specification<News> {
    private String keyWord;
    private String sortedBy;
    private int order;

    @Override
    public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        query.distinct(true);
        Predicate predicate = null;
        if (keyWord!=null) {
            predicate = criteriaBuilder.and(
                    criteriaBuilder.or(
                            criteriaBuilder.like(root.get("title"), "%" + keyWord + "%")
//                            criteriaBuilder.like(root.get("surname"), "%" + keyWord + "%")
//                        criteriaBuilder.like(root.get("mail"), "%" + keyWord + "%")
//                    criteriaBuilder.equal(root.get("id"), userSearchingDto.getName())

                    )
            );
        }
            if (order == 1) {
                query.orderBy(criteriaBuilder.asc(root.get(sortedBy)));
            }
            else if (order==2){
                query.orderBy(criteriaBuilder.desc(root.get(sortedBy)));
            }

        return predicate;

    }
}

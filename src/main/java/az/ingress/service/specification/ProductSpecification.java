package az.ingress.service.specification;

import az.ingress.dao.entity.ProductEntity;
import az.ingress.model.criteria.ProductCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import az.ingress.util.PredicateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static az.ingress.model.constants.CriteriaConstants.NAME;
import static az.ingress.model.constants.CriteriaConstants.PRICE;
import static az.ingress.model.constants.CriteriaConstants.RATING;

@AllArgsConstructor
public class ProductSpecification implements Specification<ProductEntity> {
    ProductCriteria productCriteria;
    @Override
    public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        var predicates = PredicateUtil.builder()
                .addNullSafety(
                        productCriteria.getName(), name -> cb.like(root.get(NAME),PredicateUtil.applyLikePattern(name))
                )
                .addNullSafety(
                        productCriteria.getPriceFrom(),priceFrom -> cb.greaterThanOrEqualTo(root.get(PRICE),priceFrom)
                ).addNullSafety(
                        productCriteria.getPriceTo(),priceTo -> cb.lessThanOrEqualTo(root.get(PRICE),priceTo)
                ).addNullSafety(
                        productCriteria.getRatingFrom(),ratingFrom -> cb.greaterThanOrEqualTo(root.get(RATING),ratingFrom)
                ).addNullSafety(productCriteria.getRatingTo(),ratingTo ->cb.lessThanOrEqualTo(root.get(RATING),ratingTo)
                ).build();
        return cb.and(predicates);
    }
}
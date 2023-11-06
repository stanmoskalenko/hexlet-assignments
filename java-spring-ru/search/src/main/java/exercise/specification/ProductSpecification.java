package exercise.specification;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductSpecification {

    public Specification<Product> build(ProductParamsDTO params) {
        if (params == null) {
            return null;
        }
        return withRatingGt(params.getRatingGt())
                .and(withPriceGt(params.getPriceGt()))
                .and(withPriceLt(params.getPriceLt()))
                .and(withCategoryId(params.getCategoryId()))
                .and(withTitle((params.getTitleCont())));
    }

    private Specification<Product> withRatingGt(Double rating) {
        return (root, query, cb) -> rating == null ? cb.conjunction() : cb.greaterThanOrEqualTo(root.get("rating"), rating);
    }

    private Specification<Product> withPriceGt(Integer price) {
        return (root, query, cb) -> price == null ? cb.conjunction() : cb.greaterThan(root.get("price"), price);
    }

    private Specification<Product> withPriceLt(Integer price) {
        return (root, query, cb) -> price == null ? cb.conjunction() : cb.lessThan(root.get("price"), price);
    }

    private Specification<Product> withCategoryId(Long categoryId) {
        return (root, query, cb) -> categoryId == null ? cb.conjunction() : cb.equal(root.get("category").get("id"), categoryId);
    }

    private Specification<Product> withTitle(String title) {
        System.out.println("TEST title => " + title);
        return (root, query, cb) -> title == null ? cb.conjunction() : cb.like(root.<String>get("title".toLowerCase()), title.toLowerCase());
    }
}

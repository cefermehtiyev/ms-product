package az.ingress.service.abstraction;

import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.criteria.ProductCriteria;
import az.ingress.model.dto.RatingQueueDto;
import az.ingress.model.request.ProductRequest;
import az.ingress.model.response.PageableProductResponse;
import az.ingress.model.response.ProductResponse;

import java.util.List;

public interface ProductService {
    void addProduct(ProductRequest productRequest);

    void updateRating(RatingQueueDto ratingQueueDto);

    void updateProduct(Long productId, ProductRequest productRequest);

    ProductResponse getProduct(Long id);

    List<ProductResponse> getProducts(List<Long> productsIds);

    PageableProductResponse<ProductResponse> getAllProducts(PageCriteria pageCriteria, ProductCriteria productCriteria);

}

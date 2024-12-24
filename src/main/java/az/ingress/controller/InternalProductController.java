package az.ingress.controller;

import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.criteria.ProductCriteria;
import az.ingress.model.dto.ProductIdsDto;
import az.ingress.model.request.ProductRequest;
import az.ingress.model.response.PageableProductResponse;
import az.ingress.model.response.ProductResponse;
import az.ingress.service.abstraction.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("internal/v1/products")
public class InternalProductController {
    private final ProductService productService;


    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id){
        return productService.getProduct(id);
    }

    @PostMapping
    public List<ProductResponse> getProducts(@RequestBody ProductIdsDto productIdsDto){
        return productService.getProducts(productIdsDto.getProductIds());
    }

}

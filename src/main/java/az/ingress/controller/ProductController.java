package az.ingress.controller;

import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.criteria.ProductCriteria;
import az.ingress.model.request.ProductRequest;
import az.ingress.model.response.PageableProductResponse;
import az.ingress.model.response.ProductResponse;
import az.ingress.service.abstraction.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public void addProduct(@RequestBody ProductRequest productRequest){
        productService.addProduct(productRequest);
    }
    @PutMapping
    public void updateProduct(@PathVariable Long productId, @RequestBody ProductRequest productRequest){
        productService.updateProduct(productId,productRequest);
    }

    @GetMapping
    public PageableProductResponse getAllProducts(  PageCriteria pageCriteria,  ProductCriteria productCriteria){
        return productService.getAllProducts(pageCriteria,productCriteria);
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id){
        return productService.getProduct(id);
    }

    @GetMapping("/all")
    public List<ProductResponse> getProducts(@RequestBody List<Long> productIds){
        return productService.getProducts(productIds);
    }
}

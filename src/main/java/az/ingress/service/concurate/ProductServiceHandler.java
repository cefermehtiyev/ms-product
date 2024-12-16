package az.ingress.service.concurate;

import az.ingress.dao.entity.ProductEntity;
import az.ingress.dao.repository.ProductImageRepository;
import az.ingress.dao.repository.ProductRepository;
import az.ingress.exception.NotFoundException;

import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.criteria.ProductCriteria;
import az.ingress.model.dto.RatingQueueDto;
import az.ingress.model.request.ProductRequest;
import az.ingress.model.response.PageableProductResponse;
import az.ingress.model.response.ProductResponse;
import az.ingress.service.abstraction.CacheService;
import az.ingress.service.abstraction.ProductService;
import az.ingress.service.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.exception.ErrorMessage.NOT_FOUND_EXCEPTION;
import static az.ingress.mapper.CacheDtoMapper.CACHE_DTO_MAPPER;
import static az.ingress.mapper.ProductImageMapper.PRODUCT_IMAGE_MAPPER;
import static az.ingress.mapper.ProductMapper.PRODUCT_MAPPER;

@RequiredArgsConstructor
@Service
public class ProductServiceHandler implements ProductService {
    private final ProductRepository productRepository;
    private final CacheService cacheService;

    @Override
    public void addProduct(ProductRequest productRequest) {
        var product = PRODUCT_MAPPER.buildProductEntity(productRequest);
        var productImageEntities = productRequest.getProductImageInfo().stream().map(productImageInfo -> PRODUCT_IMAGE_MAPPER.buildProductImage(productImageInfo, product)).toList();
        product.setProductImageEntities(productImageEntities);
        productRepository.save(product);

    }


    @Override
    public void updateRating(RatingQueueDto ratingQueueDto) {
        var product = fetchEntityExist(ratingQueueDto.getProductId());
        product.setRating(ratingQueueDto.getAverageRating());
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Long productId, ProductRequest productRequest) {
        var product = fetchEntityExist(productId);
        PRODUCT_MAPPER.updateProduct(product,productRequest);
        productRepository.save(product);
    }

    @Override
    public ProductResponse getProduct(Long productId) {
        var cacheData = cacheService.getCache(productId);
        if (cacheData != null) {
            return PRODUCT_MAPPER.buildProductResponse(cacheData);
        } else {
            var product = fetchEntityExist(productId);
            cacheService.saveToCache(productId, CACHE_DTO_MAPPER.buildCacheDto(product));
            return PRODUCT_MAPPER.buildProductResponse(product);
        }

    }

    @Override
    public List<ProductResponse> getProducts(List<Long> productsIds) {
        return productsIds.stream().map(id -> PRODUCT_MAPPER.buildProductResponse(fetchEntityExist(id))).toList();
    }

    @Override
    public PageableProductResponse<ProductResponse> getAllProducts(PageCriteria pageCriteria, ProductCriteria productCriteria) {
        var productPage = productRepository.findAll(
                new ProductSpecification(productCriteria),
                PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount(), Sort.by("id").descending())
        );
        return PRODUCT_MAPPER.buildPageableProductResponse(productPage);
    }

    private ProductEntity fetchEntityExist(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new NotFoundException(NOT_FOUND_EXCEPTION.getMessage())
        );
    }
}

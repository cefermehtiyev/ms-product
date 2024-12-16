package az.ingress.mapper;

import az.ingress.dao.entity.ProductEntity;
import az.ingress.model.dto.CacheDto;
import az.ingress.model.request.ProductRequest;
import az.ingress.model.response.PageableProductResponse;
import az.ingress.model.response.ProductResponse;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Collections;

import static az.ingress.mapper.ProductImageMapper.PRODUCT_IMAGE_MAPPER;

public enum ProductMapper {
    PRODUCT_MAPPER;

    public ProductEntity buildProductEntity(ProductRequest productRequest){
        return ProductEntity.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .rating(BigDecimal.valueOf(0.00))
                .categoryId(productRequest.getCategoryId())
                .build();

    }



    public ProductResponse buildProductResponse(ProductEntity productEntity){
        return ProductResponse.builder()
                .name(productEntity.getName())
                .quantity(productEntity.getQuantity())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .rating(productEntity.getRating())
                .build();
    }

    public ProductResponse buildProductResponse(CacheDto cacheDto){
        return ProductResponse.builder()
                .name(cacheDto.getName())
                .quantity(cacheDto.getQuantity())
                .description(cacheDto.getDescription())
                .price(cacheDto.getPrice())
                .rating(cacheDto.getRating())
                .build();
    }

    public void updateProduct(ProductEntity productEntity,ProductRequest productRequest){
        productEntity.setName(productRequest.getName());
        productEntity.setProductImageEntities(productRequest.getProductImageInfo().stream().map(productImageInfo -> PRODUCT_IMAGE_MAPPER.buildProductImage(productImageInfo,productEntity)).toList());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setDescription(productRequest.getDescription());
        productEntity.setQuantity(productRequest.getQuantity());
        productEntity.setCategoryId(productEntity.getCategoryId());

    }


    public PageableProductResponse<ProductResponse> buildPageableProductResponse(Page<ProductEntity> productEntityPage){
        return PageableProductResponse.<ProductResponse>builder().product(productEntityPage.map(this::buildProductResponse).toList())
                .hasNextPage(productEntityPage.hasNext())
                .lastPageNumber(productEntityPage.getNumber()).lastPageNumber(productEntityPage.getTotalPages())
                .build();

    }
}

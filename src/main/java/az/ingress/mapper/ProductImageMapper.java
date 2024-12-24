package az.ingress.mapper;

import az.ingress.dao.entity.ProductEntity;
import az.ingress.dao.entity.ProductImageEntity;
import az.ingress.model.request.ProductImageInfo;

public enum ProductImageMapper {
    PRODUCT_IMAGE_MAPPER;

    public ProductImageEntity buildProductImage(ProductImageInfo productImageInfo, ProductEntity productEntity){
        return ProductImageEntity.builder()
                .product(productEntity)
                .imageUrl(productImageInfo.getImageUrl())
                .imageSequence(productImageInfo.getImageSequence())
                .size(productImageInfo.getSize())
                .build();
    }

}

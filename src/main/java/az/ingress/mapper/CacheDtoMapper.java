package az.ingress.mapper;

import az.ingress.dao.entity.ProductEntity;
import az.ingress.model.dto.CacheDto;

public enum CacheDtoMapper {
    CACHE_DTO_MAPPER;

    public CacheDto buildCacheDto(ProductEntity productEntity){
        return CacheDto.builder()
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .description(productEntity.getDescription())
                .quantity(productEntity.getQuantity())
                .rating(productEntity.getRating()).build();
    }


}

package az.ingress.service.abstraction;

import az.ingress.model.dto.CacheDto;

import java.time.temporal.TemporalUnit;
import java.util.Stack;

public interface CacheService {
    CacheDto getCache(Long productId);

    void saveToCache(Long productId, CacheDto cacheDto);
}

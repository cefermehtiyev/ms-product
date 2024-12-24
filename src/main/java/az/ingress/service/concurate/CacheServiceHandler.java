package az.ingress.service.concurate;

import az.ingress.model.constants.CacheKey;
import az.ingress.model.dto.CacheDto;
import az.ingress.service.abstraction.CacheService;
import az.ingress.util.CacheUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import static az.ingress.model.constants.CacheKey.CACHE_KEY;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheServiceHandler implements CacheService {

    private final CacheUtil cacheUtil;


    @Override
    public CacheDto getCache(Long productId) {
        var cacheKey = CACHE_KEY + productId;
        var cacheDto = cacheUtil.getBucket(cacheKey);
        log.info("ActionLog.getCache :{}", productId);
        return (CacheDto) cacheDto;
    }

    @Override
    public void saveToCache(Long productId, CacheDto cacheDto) {
        var cacheKey = CACHE_KEY + productId;
        cacheUtil.saveToCache(cacheKey, cacheDto, 1L, ChronoUnit.HOURS);
        log.info("ActingLog.saveToCache :{}", cacheDto);
    }
}

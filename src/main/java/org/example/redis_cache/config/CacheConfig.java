package org.example.redis_cache.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
@EnableCaching // Spring Cache 기능 활성화
public class CacheConfig {
    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(
                                new GenericJackson2JsonRedisSerializer()
                        )
                ) // redis에 객체를 저장 할 수 있도록 json 형태로 직렬화 하여 저장하는 옵션  ex : {"id":1,"name":"홍길동","email":"hong@test.com"}
                .entryTtl(Duration.ofMinutes(5)) // 캐시 TTL: 5분
                .disableCachingNullValues(); // null 결과값은 캐싱하지 않도록 함.
    }
}

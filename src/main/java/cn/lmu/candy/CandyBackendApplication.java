package cn.lmu.candy;

import cn.lmu.candy.test.RedisConnectionTest;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@EnableCaching
@SpringBootApplication
public class CandyBackendApplication implements CommandLineRunner {

    @Autowired
    private RedisConnectionTest redisConnectionTest;

    public static void main(String[] args) {
        SpringApplication.run(CandyBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 异步调用 Redis 连接测试
        new Thread(() -> redisConnectionTest.testConnection()).start();
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 使用 String 和 JSON 的序列化器
        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer =
                new GenericJackson2JsonRedisSerializer(getObjectMapper());

        // Redis 缓存默认配置
        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofDays(1)) // 默认缓存时间为 1 天
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer)) // Key 使用 String 序列化
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(genericJackson2JsonRedisSerializer)) // Value 使用 JSON 序列化
                .disableCachingNullValues();

        // Redis 缓存管理器
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defaultConfig) // 默认配置
                .build();
    }

    /**
     * 配置 ObjectMapper，用于 JSON 序列化
     */
    private ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(
                objectMapper.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.NON_FINAL
        );
        return objectMapper;
    }
}

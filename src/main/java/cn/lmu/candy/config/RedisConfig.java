package cn.lmu.candy.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    @SuppressWarnings("all")
    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(factory);// 配置连接⼯⼚
// 使⽤Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使⽤JDK的序列化⽅式）
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();//⾃定义ObjectMapper
// 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
// 指定序列化输⼊的类型，类必须是⾮final修饰的，final修饰的类，⽐如String,Integer等会抛出异常
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//String序列化配置
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
// key采⽤String的序列化⽅式
        template.setKeySerializer(stringRedisSerializer);
// hash的key也采⽤String的序列化⽅式
        template.setHashKeySerializer(stringRedisSerializer);
// value序列化⽅式采⽤jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
// hash的value序列化⽅式采⽤jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
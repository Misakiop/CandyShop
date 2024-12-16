package cn.lmu.candy.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisConnectionTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 测试 Redis 连接
    public void testConnection() {
        // 测试写入 Redis
        redisTemplate.opsForValue().set("testKey", "Hello Redis!");
        System.out.println("Successfully set testKey in Redis.");

        // 测试读取 Redis
        String value = (String) redisTemplate.opsForValue().get("testKey");
        System.out.println("Successfully retrieved value from Redis: " + value);

        // 测试删除 Redis
        redisTemplate.delete("testKey");
        System.out.println("Successfully deleted testKey from Redis.");

        // 再次尝试获取数据
        value = (String) redisTemplate.opsForValue().get("testKey");
        System.out.println("Retrieved value after deletion: " + value); // 应该返回 null
    }
}

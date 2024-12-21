package cn.lmu.candy.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis测试类
 */
@Component
public class RedisConnectionTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 测试 Redis 连接
    public void testConnection() {
        try {
            // 测试写入 Redis
            redisTemplate.opsForValue().set("testKey", "Hello Redis!");
            String writtenValue = (String) redisTemplate.opsForValue().get("testKey");
            if ("Hello Redis!".equals(writtenValue)) {
                System.out.println("Successfully set testKey in Redis.");
            } else {
                System.err.println("Failed to set testKey in Redis.");
            }

            // 测试读取 Redis
            String value = (String) redisTemplate.opsForValue().get("testKey");
            if (value != null) {
                System.out.println("Successfully retrieved value from Redis: " + value);
            } else {
                System.err.println("Failed to retrieve testKey from Redis.");
            }

            // 测试删除 Redis
            boolean isDeleted = redisTemplate.delete("testKey");
            if (isDeleted) {
                System.out.println("Successfully deleted testKey from Redis.");
            } else {
                System.err.println("Failed to delete testKey from Redis.");
            }

            // 再次尝试获取数据
            value = (String) redisTemplate.opsForValue().get("testKey");
            if (value == null) {
                System.out.println("Value successfully removed from Redis, no data found for testKey.");
            } else {
                System.err.println("Value still exists after deletion: " + value);
            }
        } catch (Exception e) {
            System.err.println("An error occurred during Redis operations: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

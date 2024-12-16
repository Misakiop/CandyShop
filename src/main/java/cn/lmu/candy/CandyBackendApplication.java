package cn.lmu.candy;

import cn.lmu.candy.test.RedisConnectionTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
public class CandyBackendApplication implements CommandLineRunner {

    @Autowired
    private RedisConnectionTest redisConnectionTest;

    public static void main(String[] args) {
        SpringApplication.run(CandyBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 调用 Redis 连接测试
        redisConnectionTest.testConnection();
    }

}

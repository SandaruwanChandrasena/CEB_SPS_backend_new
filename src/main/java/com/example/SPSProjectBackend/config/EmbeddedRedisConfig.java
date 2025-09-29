//package com.example.SPSProjectBackend.config;
//
//import org.springframework.context.annotation.Configuration;
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import redis.embedded.RedisServer;
//import java.io.IOException;
//
//@Configuration
//public class EmbeddedRedisConfig {
//
//    private RedisServer redisServer;
//
//    @PostConstruct
//    public void startRedis() throws IOException {
//        redisServer = new RedisServer(6379);// Use your preferred port
//        redisServer.start();
//    }
//
//    @PreDestroy
//    public void stopRedis() {
//        if (redisServer != null) {
//            redisServer.stop();
//        }
//    }
//}


package com.example.SPSProjectBackend.config;

import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;
import java.io.IOException;

@Configuration
public class EmbeddedRedisConfig {

    private RedisServer redisServer;

    @PostConstruct
    public void startRedis() throws IOException {
        redisServer = new RedisServerBuilder()
                .port(6379) // Use your preferred port
                .setting("maxheap 256mb") // ✅ Reduce heap to avoid Windows error 0x5AF
                .build();
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}
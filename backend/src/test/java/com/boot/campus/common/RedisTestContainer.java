package com.boot.campus.common;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class RedisTestContainer {
    
    static {
        GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis:7.0.8-alpine"))
                .withExposedPorts(6379);
        redis.start();
        System.setProperty("spring.data.redis.host", redis.getHost());
        System.setProperty("spring.data.redis.port", redis.getMappedPort(6379).toString());
    }
}

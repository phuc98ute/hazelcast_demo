package com.example.demoapp.config;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class App1Config {

    @Bean
    HazelcastInstance hazelcastInstanceHz() {
        return Hazelcast.newHazelcastInstance();
    }
}

package com.example.demoapp.config;

import com.example.demoapp.infrastructure.portable.CustomerPortableFactory;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class App2Config {
    @Bean
    HazelcastInstance hazelcastInstanceHz() {
        return Hazelcast.newHazelcastInstance();
    }

    @Bean
    HazelcastInstance portableHazelcastInstance() {
        Config config = new Config();
        config.getSerializationConfig().addPortableFactory( 1, new CustomerPortableFactory() );
        return Hazelcast.newHazelcastInstance(config);
    }
}

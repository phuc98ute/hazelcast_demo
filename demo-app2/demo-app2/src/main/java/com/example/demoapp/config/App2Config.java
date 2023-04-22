package com.example.demoapp.config;

import com.example.demoapp.infrastructure.Customer;
import com.example.demoapp.infrastructure.portable.CustomerPortableFactory;
import com.example.demoapp.listener.HazelcastEntryListener;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
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
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        IMap<String, Customer> map = hazelcastInstance.getMap("customerMap-1");
        map.addEntryListener(new HazelcastEntryListener(), true);

        IMap<String, Customer> stringMap = hazelcastInstance.getMap("simpleString-1");
        stringMap.addEntryListener(new HazelcastEntryListener(), true);

        return hazelcastInstance;
    }
}

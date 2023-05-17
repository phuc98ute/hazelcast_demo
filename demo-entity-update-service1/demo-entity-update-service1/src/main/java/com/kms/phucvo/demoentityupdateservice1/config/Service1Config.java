package com.kms.phucvo.demoentityupdateservice1.config;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.kms.phucvo.demoentityupdateservice1.listener.HazelcastEntryListener;
import com.kms.phucvo.infrastructure.HazelcastData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Service1Config {

    @Bean
    HazelcastInstance hazelcastInstanceHz() {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();

        IMap<String, String> simpleStringMap = hazelcastInstance.getMap("simpleStringMap-1");
        simpleStringMap.addEntryListener(new HazelcastEntryListener(), true);

        IMap<String, HazelcastData> customObjectMap = hazelcastInstance.getMap("customObjectMap-1");
        customObjectMap.addEntryListener(new HazelcastEntryListener(), true);

        return hazelcastInstance;
    }
}

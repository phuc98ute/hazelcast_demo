package vn.kms.phucvo.hazelcast_demo.config;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.kms.phucvo.hazelcast_demo.infrastructure.Customer;
import vn.kms.phucvo.hazelcast_demo.listener.HazelcastEntryListener;

@Configuration
public class AppDemo1Config {
    @Bean
    HazelcastInstance hazelcastInstanceHz() {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();

        IMap<String, String> simpleStringMap = hazelcastInstance.getMap("simpleStringMap-1");
        simpleStringMap.addEntryListener(new HazelcastEntryListener(), true);

        IMap<String, Customer> customObjectMap = hazelcastInstance.getMap("customObjectMap-1");
        customObjectMap.addEntryListener(new HazelcastEntryListener(), true);

        return hazelcastInstance;
    }
}

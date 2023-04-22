package vn.kms.phucvo.hazelcast_demo.controller;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.kms.phucvo.hazelcast_demo.infrastructure.Customer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Controller
@Slf4j
@RequestMapping("hz/map")
public class MapDataController {

    Map<String, String> bigMap = new HashMap<>();

    @PostConstruct
    public void createBigMap() {
        IntStream.range(0, 1000000).forEach(index -> {
                bigMap.put(String.valueOf(index), LocalDateTime.now().toString());
                }
        );
    }

    @Autowired
    HazelcastInstance hazelcastInstanceHz;

    @GetMapping("/updateString")
    public ResponseEntity updateSimpleString(@RequestParam String message) {
        IMap<String, String> map = hazelcastInstanceHz.getMap("simpleStringMap-1");

        map.put("message", message);
        return new ResponseEntity("Update message to hazelcast map success!" + message, HttpStatus.OK);
    }

    @GetMapping("/updateBigMap")
    public ResponseEntity updateBigMap() {
        IMap<String, String> map = hazelcastInstanceHz.getMap("simpleStringMap-1");
        long startTime = System.nanoTime();
        map.putAll(bigMap);
        long endTime = System.nanoTime();
        log.info("Total runtime to insert 1 million record to hazelcast map: "+ (endTime-startTime));
        return new ResponseEntity("Update bigMap to hazelcast map success!", HttpStatus.OK);
    }

    @GetMapping("updateCustomer")
    public ResponseEntity updateCustomer() {
        IMap<String, Customer> map = hazelcastInstanceHz.getMap("customerMap-1");
        map.put("1", new Customer("john", 30, true));
        map.put("2", new Customer("alan", 30, false));
        map.put("3", new Customer("alex", 47, true));
        map.put("4", new Customer("ana", 40, false));

        return new ResponseEntity("Update customer data to hazelcast map success!" + map, HttpStatus.OK);
    }
}

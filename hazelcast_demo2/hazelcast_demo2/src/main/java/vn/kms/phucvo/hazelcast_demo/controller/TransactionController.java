package vn.kms.phucvo.hazelcast_demo.controller;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hz/transaction")
@Slf4j
public class TransactionController {

    @Autowired
    HazelcastInstance hazelcastInstanceHz;

    @GetMapping("/tryUpdate")
    public ResponseEntity<String> tryUpdateInTransaction() {
        IMap<String, String> map = hazelcastInstanceHz.getMap("transactionMap-1");
        try {
            map.put("5","5");
        } catch (Exception e) {
            log.error("Error when put data to map", e);
        }

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}

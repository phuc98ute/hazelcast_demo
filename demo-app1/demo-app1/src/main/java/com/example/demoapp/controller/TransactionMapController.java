package com.example.demoapp.controller;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.internal.util.StringUtil;
import com.hazelcast.map.IMap;
import com.hazelcast.transaction.TransactionContext;
import com.hazelcast.transaction.TransactionalMap;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/hz/transaction")
public class TransactionMapController {

    @Autowired
    HazelcastInstance hazelcastInstanceHz;

    @GetMapping("/get")
    public ResponseEntity<String> getTransactionMap() {
        IMap<String, String> map = hazelcastInstanceHz.getMap("transactionMap-1");
        String mapAsString = map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
        System.out.println(mapAsString);
        return new ResponseEntity<String>(mapAsString, HttpStatus.OK);
    }

    @GetMapping("/process")
    public ResponseEntity<String> processTransaction() {
        TransactionContext txCxt = hazelcastInstanceHz.newTransactionContext();
        txCxt.beginTransaction();
        TransactionalMap<String, String> map = txCxt.getMap("transactionMap-1");
        try {
            map.put("1", "1");
            map.put("2", "2");
            txCxt.commitTransaction();
        } catch (Throwable t) {
            txCxt.rollbackTransaction();
        }
        System.out.println("Finished");
        return new ResponseEntity<>("Finished", HttpStatus.OK);
    }

    @GetMapping("/process-2")
    public ResponseEntity<String> processTransaction2(@RequestParam boolean active) {
        TransactionContext txCxt = hazelcastInstanceHz.newTransactionContext();
        txCxt.beginTransaction();
        TransactionalMap<String, String> map = txCxt.getMap("transactionMap-1");
        try {
            map.put("3", "3");
            map.put("4", "4");

            if (active) throw new RuntimeException("Exception when run transaction");

            txCxt.commitTransaction();
        } catch (Exception e) {
            txCxt.rollbackTransaction();
            return new ResponseEntity<>("Transaction failed", HttpStatus.BAD_REQUEST);
        }
        System.out.println("Finished");
        return new ResponseEntity<>("Finished", HttpStatus.OK);
    }

    @GetMapping("/process-long")
    public ResponseEntity<String> processTransactionLong(@RequestParam boolean active) {

        TransactionContext txCxt = hazelcastInstanceHz.newTransactionContext();
        txCxt.beginTransaction();
        TransactionalMap<String, String> map = txCxt.getMap("transactionMap-1");
        try {
            System.out.println("Put 5");
            map.put("5", "5");
            Thread.sleep(5000);
            System.out.println("Put 6");
            map.put("6", "6");
            Thread.sleep(10000);
            System.out.println("Put 7");
            map.put("7", "7");
            Thread.sleep(5000);
            System.out.println("Put 8");
            map.put("8", "8");
            Thread.sleep(5000);
            System.out.println("Put 9");
            map.put("9", "9");
            Thread.sleep(5000);
            System.out.println("Put 10");
            map.put("10", "10");

            if (active) throw new RuntimeException("Exception when run transaction");

            txCxt.commitTransaction();
        } catch (Exception e) {
            txCxt.rollbackTransaction();
            return new ResponseEntity<>("Transaction failed", HttpStatus.BAD_REQUEST);
        }
        System.out.println("Finished");
        return new ResponseEntity<>("Finished", HttpStatus.OK);
    }

    @GetMapping("/reset")
    public ResponseEntity<String> reset() {
        IMap<String, String> map = hazelcastInstanceHz.getMap("transactionMap-1");
        map.destroy();
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}

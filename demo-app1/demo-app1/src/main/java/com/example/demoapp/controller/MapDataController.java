package com.example.demoapp.controller;

import com.example.demoapp.infrastructure.Customer;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("hz/map")
public class MapDataController {

    @Autowired
    HazelcastInstance hazelcastInstanceHz;

    @GetMapping("/updateString")
    public ResponseEntity updateSimpleString(@RequestParam String message) {
        IMap<String, String> map = hazelcastInstanceHz.getMap("simpleString-1");

        map.put("message", message);
        return new ResponseEntity("Update message to hazelcast map success!" + message, HttpStatus.OK);
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

package com.example.demoapp.controller;

import com.example.demoapp.infrastructure.Customer;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.query.impl.predicates.SqlPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/hz/map")
public class DataMapController {
    @Autowired
    HazelcastInstance hazelcastClient;

    @GetMapping("/customer")
    public ResponseEntity getCustomer(@RequestParam String id) {
        IMap<String, Customer> map = hazelcastClient.getMap("customerMap-1");

        Customer customer = map.get(id);

        return new ResponseEntity("Get customer from hazelcast map success: " + customer, HttpStatus.OK);
    }

    @PostMapping("/customerByQuery")
    public ResponseEntity getCustomers(@RequestBody String query) {
        IMap<String, Customer> map = hazelcastClient.getMap("customerMap-1");

        Set<Customer> customers = (Set<Customer>) map.values(new SqlPredicate(query));

        return new ResponseEntity("Get customer from hazelcast map success: " + customers, HttpStatus.OK);
    }
}

package com.example.demoapp.controller;

import com.example.demoapp.infrastructure.Customer;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
import com.hazelcast.query.Predicates;
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
    HazelcastInstance hazelcastInstanceHz;

    @GetMapping("/welcome")
    public ResponseEntity getString() {
        IMap<String, String> map = hazelcastInstanceHz.getMap("simpleString-1");

        String message = map.get("message");
        return new ResponseEntity("Get message from hazelcast map success with message: " + message, HttpStatus.OK);
    }

    @GetMapping("/customer")
    public ResponseEntity getCustomer(@RequestParam String id) {
        IMap<String, Customer> map = hazelcastInstanceHz.getMap("customerMap-1");

        Customer customer = map.get(id);

        return new ResponseEntity("Get customer from hazelcast map success: " + customer, HttpStatus.OK);
    }

    @PostMapping("/customerBySql")
    public ResponseEntity getCustomers(@RequestBody String query) {
        IMap<String, Customer> map = hazelcastInstanceHz.getMap("customerMap-1");
        Set<Customer> customers = (Set<Customer>) map.values(Predicates.sql(query));

        return new ResponseEntity("Get customer from hazelcast map success: " + customers, HttpStatus.OK);
    }

    @PostMapping("/customerByLagecyPredicate")
    public ResponseEntity getCustomerByLagecyPridicate() {
        IMap<String, Customer> map = hazelcastInstanceHz.getMap("customerMap-1");
        PredicateBuilder.EntryObject e = Predicates.newPredicateBuilder().getEntryObject();
        Predicate predicate = e.is("active").and(e.get("age").lessThan(30));
        Set<Customer> customers = (Set<Customer>) map.values(predicate);

        return new ResponseEntity("Get customer from hazelcast map success: " + customers, HttpStatus.OK);
    }
}

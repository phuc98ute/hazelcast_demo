package com.example.demoapp.controller;

import com.example.demoapp.infrastructure.EchoTask;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hz/task")
public class EchoTaskController {

    @Autowired
    HazelcastInstance hazelcastInstanceHz;

    @GetMapping("/run")
    public ResponseEntity run() throws InterruptedException {

        IExecutorService executor = hazelcastInstanceHz.getExecutorService("exec");
        for (int k = 1; k <= 10; k++) {
            Thread.sleep(1000);
            System.out.println("Producing echo task: " + k);
            executor.execute(new EchoTask("" + k));
        }
        return new ResponseEntity("EchoTaskMain finished!", HttpStatus.OK);
    }
}

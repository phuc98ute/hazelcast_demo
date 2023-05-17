package com.kms.phucvo.demoentityupdateservice2.controller;

import com.google.gson.Gson;
import com.hazelcast.core.HazelcastInstance;
import com.kms.phucvo.infrastructure.HazelcastData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/hz/map")
@Slf4j
public class Service2Controller {

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @GetMapping("/update")
    public String updateMapData() {
        HazelcastData hazelcastData = HazelcastData.builder().name("test1").age("20").address("test address 1").additionalData("123").build();
        HazelcastData hazelcastData2 = HazelcastData.builder().name("test2").age("30").address("test address 2").additionalData("123").build();
        HazelcastData hazelcastData3 = HazelcastData.builder().name("test3").age("40").address("test address 3").additionalData("123").build();
        HazelcastData hazelcastData4 = HazelcastData.builder().name("test4").age("50").address("test address 4").additionalData("123").build();

        Map<String, HazelcastData> mapData = hazelcastInstance.getMap("customObjectMap-1");
        mapData.put("test1",hazelcastData);
        mapData.put("test2",hazelcastData2);
        mapData.put("test3",hazelcastData3);
        mapData.put("test4",hazelcastData4);

        return "Update success";
    }

    @GetMapping("/get")
    public String getMapData() {
        Map<String, HazelcastData> mapData = hazelcastInstance.getMap("customObjectMap-1");
        mapData.entrySet().stream().forEach(item -> {
            log.info(item.toString());
        });
        return new Gson().toJson(mapData);
    }
}

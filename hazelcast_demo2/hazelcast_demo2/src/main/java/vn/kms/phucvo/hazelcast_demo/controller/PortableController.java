package vn.kms.phucvo.hazelcast_demo.controller;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.kms.phucvo.hazelcast_demo.infrastructure.portable.PortableCustomer;

@Controller
@RequestMapping("/hz/portable")
public class PortableController {

    @Autowired
    HazelcastInstance portableHazelcastInstance;

    @GetMapping("/updateMap")
    public ResponseEntity<String> updateMap() {
        IMap<String, PortableCustomer> map = portableHazelcastInstance.getMap("portableCustomerMap-1");
        map.put("1", new PortableCustomer("john", 30, true));
        map.put("2", new PortableCustomer("alan", 30, false));
        map.put("3", new PortableCustomer("alex", 47, true));
        map.put("4", new PortableCustomer("ana", 40, false));

        return new ResponseEntity("Update customer data to hazelcast map success!" + map, HttpStatus.OK);
    }
}

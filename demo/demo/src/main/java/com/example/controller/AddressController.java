package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rahul
 */
@Slf4j
@RestController
@RequestMapping("/api/address")
public class AddressController {

    @GetMapping("getAddress")
    public String getAddress() {
        log.error("Inside Error");
        log.warn("Inside Warning");
        log.info("Inside Info");
        log.debug("Inside Debug");
        log.trace("Inside Trace");
        
        return "This is sample student address";
    }
}

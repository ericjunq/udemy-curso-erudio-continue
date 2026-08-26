package com.ericjunq.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestLogController {

    private final Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());

    @GetMapping("/test")
    public String testLog(){
        logger.debug("DEBUG Log");
        logger.info("DEBUG Log");
        logger.warn("DEBUG Log");
        logger.error("DEBUG Log");

        return "Logs generated successfully";
    }
}

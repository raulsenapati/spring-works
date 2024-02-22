package com.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author rahul
 */
@Slf4j
@Service
public class DemoScheduler {

    /**
     * www.cronmaker.com
     * Generated Cron Expression for every 1 minute
     * first 5 parameters only
     */
    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void firstScheduler() {
        log.info("DemoScheduler = {}", new Date());
    }
}

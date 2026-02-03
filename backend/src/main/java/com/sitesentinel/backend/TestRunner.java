//package com.sitesentinel.backend;
//
//import com.sitesentinel.backend.service.PlayWrightService;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class TestRunner implements CommandLineRunner {
//
//    private final PlayWrightService playwrightService;
//
//    public TestRunner(PlayWrightService playwrightService) {
//        this.playwrightService = playwrightService;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("--- TESTING PLAYWRIGHT INTEGRATION ---");
//        // Let's test checking linkedIn
//        playwrightService.performCheck("https://www.yahoo.com");
//        System.out.println("--------------------------------------");
//    }
//}
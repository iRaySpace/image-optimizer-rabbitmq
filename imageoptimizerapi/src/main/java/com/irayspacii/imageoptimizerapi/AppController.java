package com.irayspacii.imageoptimizerapi;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AppController {

    @Autowired
    private AppService appService;

    @PostMapping("/store")
    public ResponseEntity store(@RequestBody byte[] imageData, HttpServletRequest request) throws Exception {
        final String storeKey = appService.store(imageData);
        return ResponseEntity.created(URI.create("/download/" + storeKey)).build();
    }

    @PostMapping("/process")
    public void process(@RequestBody AppDto data) {
        System.out.println(data);
    }
}

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

    @PostMapping("/process")
    public ResponseEntity process(@RequestBody byte[] imageData) throws Exception {
        final String storeKey = appService.process(imageData);
        return ResponseEntity.created(URI.create(storeKey)).build();
    }

}

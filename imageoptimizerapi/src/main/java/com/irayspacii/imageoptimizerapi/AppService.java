package com.irayspacii.imageoptimizerapi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class AppService {
    
    public String store(byte[] imageData) throws IOException {
        final String key = generateRandomString();
        final Path path = Paths.get("/tmp/" + key);
        Files.write(path, imageData);
        return key;
    }

    private String generateRandomString() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}

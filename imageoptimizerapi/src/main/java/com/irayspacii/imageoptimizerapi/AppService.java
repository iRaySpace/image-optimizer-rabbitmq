package com.irayspacii.imageoptimizerapi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public String process(byte[] imageData) throws IOException {
        final String key = generateRandomString();
        final Path path = Paths.get("/tmp/" + key);
        Files.write(path, imageData);
        rabbitTemplate.convertAndSend("image.uploaded", key);
        return key;
    }

    private String generateRandomString() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}

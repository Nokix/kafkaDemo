package com.example.kafkademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
    @Autowired
    KafkaTemplate<String, String> template;

    @GetMapping("/kafka/{message}")
    public void postToKafka(@PathVariable String message) {
        template.send("confluent", message);
    }

    @KafkaListener(id = "V2", topics = "confluent")
    public void listen(String value) {
        System.out.println(value);
    }
}
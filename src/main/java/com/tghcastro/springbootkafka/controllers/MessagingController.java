package com.tghcastro.springbootkafka.controllers;

import com.tghcastro.springbootkafka.SpringBootKafkaApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("message")
public class MessagingController {

    @Value(value = "${application.kafka.topic}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping()
    public String getMessages() {
        return String.join(",", SpringBootKafkaApplication.consumedMessages);
    }

    @PostMapping()
    public void sendMessage(@RequestParam(name = "newMessage") String message) {
        kafkaTemplate.send(topicName, message);
    }

    @PostMapping("/async")
    public void sendMessage2(@RequestParam(name = "newMessage") String message) {

        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topicName, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }

}

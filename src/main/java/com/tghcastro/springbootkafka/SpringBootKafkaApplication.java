package com.tghcastro.springbootkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBootKafkaApplication {

	public static List<String> consumedMessages = new ArrayList<>();

	@KafkaListener(topics = "spring-kafka-topic")
	public void consumeMessage(String message) {
		consumedMessages.add(message);
		System.out.println("Consumed message: " + message);
	}

	public static void main(String[] args) {

		SpringApplication.run(SpringBootKafkaApplication.class, args);


	}

}

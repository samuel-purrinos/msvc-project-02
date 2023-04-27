package com.uichesoh.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class MessageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageServiceApplication.class, args);
	}
	@RabbitListener(queues = {"${uichesoh.queue.name}"})
	public void receiveMessageWithRabbitMq(String message){
		log.info("Message received from order service '{}'",message);

	}

}

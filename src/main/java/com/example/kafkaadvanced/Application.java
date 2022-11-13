package com.example.kafkaadvanced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka                                      //консьюмер
@SpringBootApplication
public class Application {

    @KafkaListener(topics = "msg")               //слушатель
    public void msgListener(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

}

package com.example.kafkaadvanced;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("msg")
public class MessageController {                                            //продюсер

    @Autowired                                                              //инициализация kafkaTemplate
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping                                                         //Контроллер мапится на localhost:8080/msg, в теле запроса передаётся ключ и само сообщений
    public void sendOrder(String msgId, String msg){
        //kafkaTemplate.send("msg", msgId, msg);                           //Имеется несколько перегруженных вариантов данного метода. Используем в нашем проекте вариант с 3 параметрами — send(String topic, K key, V data). Так как KafkaTemplate типизирован String-ом, то ключ и данные в методе send будут являться строкой. Первым параметром указывается топик, то есть тема, в которую будут отправляться сообщения, и на которую могут подписываться консьюмеры, чтобы их получать.
        ListenableFuture<SendResult<String,String>> future = kafkaTemplate.send("msg", msgId, msg);
        future.addCallback(System.out::println, System.err::println);     //Метод addCallback() принимает два параметра – SuccessCallback и FailureCallback. Оба они являются функциональными интерфейсами. Из названия можно понять, что метод первого будет вызван в результате успешной отправки сообщения, второго – в результате ошибки
        kafkaTemplate.flush();                                            //метод println() класса помещает значение в буфер, а уже при flush() толкает в System.out
    }
}

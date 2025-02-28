package com.ragheb.kafka.consumer;

import com.ragheb.kafka.payload.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    //@KafkaListener(topics = "ragheb", groupId = "myGroup")
    public void consumeMessage(String message) {
       log.info("Consuming the message from ragheb topic:: {}", message);
    }

    @KafkaListener(topics = "ragheb", groupId = "myGroup")
    public void consumeJsonMessage(Student student) {
        log.info("Consuming the message from ragheb topic:: {}", student.toString());
    }
}

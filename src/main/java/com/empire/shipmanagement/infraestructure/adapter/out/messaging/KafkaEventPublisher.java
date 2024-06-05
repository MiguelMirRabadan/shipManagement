package com.empire.shipmanagement.infraestructure.adapter.out.messaging;

import com.empire.shipmanagement.business.core.application.event.Event;
import com.empire.shipmanagement.business.core.domain.model.EventType;
import com.empire.shipmanagement.business.core.domain.port.io.EventPublisher;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Log4j2
public class KafkaEventPublisher implements EventPublisher {
    @Autowired
    private KafkaTemplate<String, Event<?>> kafkaTemplate;
    @Value("${topic.name}")
    private String topic;

    @Override
    public <T> void publish(String id, EventType type, T data) {
        log.info(String.format("publishing data to: %s",topic));
        kafkaTemplate.send(topic, Event.builder()
                .id(id != null ? id : UUID.randomUUID().toString())
                .date(LocalDate.now())
                .type(type)
                .data(data)
                .build());
    }
}

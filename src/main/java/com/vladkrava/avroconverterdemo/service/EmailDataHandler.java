package com.vladkrava.avroconverterdemo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.vladkrava.avroconverterdemo.domain.EmailData;

/**
 * Performs user registration flow
 *
 * @author vkrava4
 * @since 0.0.1-SNAPSHOT
 */
@Service
public class EmailDataHandler {

    private final KafkaTemplate<String, EmailData> kafkaTemplate;

    @Value(value = "${app.user.data.processor.topic}")
    private String userDataProcessorTopic;


    public EmailDataHandler(final KafkaTemplate<String, EmailData> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Performs user registration flow by sending {@link EmailData} object
     * to Kafka topics for processing
     *
     * @param data Apache Avro generated object which holds the information about user
     * @return {@code EmailData} object which holds the information about user with some possible modifications
     */
    public EmailData handle(final EmailData data) {
        kafkaTemplate.send(userDataProcessorTopic, data.getUsername(), data);
        return data;
    }
}

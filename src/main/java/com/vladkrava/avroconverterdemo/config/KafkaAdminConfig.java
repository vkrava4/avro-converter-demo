package com.vladkrava.avroconverterdemo.config;

import static org.apache.kafka.clients.admin.AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

/**
 * Spring configuration class for Kafka {@link KafkaAdmin} client to create defined topics
 *
 * @author vkrava4
 * @since 0.0.1-SNAPSHOT
 */
@Configuration
public class KafkaAdminConfig {

    @Value(value = "${kafka.bootstrap.address}")
    private String bootstrapAddress;

    @Value(value = "${app.user.data.processor.topic}")
    private String userDataProcessorTopic;


    @Bean
    public KafkaAdmin kafkaAdmin() {
        final Map<String, Object> configs = new HashMap<>();
        configs.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic userDataProcessorTopic() {
        return new NewTopic(userDataProcessorTopic, 1, (short) 1);
    }
}

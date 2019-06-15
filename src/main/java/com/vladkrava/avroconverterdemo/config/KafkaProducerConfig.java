package com.vladkrava.avroconverterdemo.config;

import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.vladkrava.avroconverterdemo.domain.EmailData;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

/**
 * Spring configuration class for configuring {@link DefaultKafkaProducerFactory}
 *
 * @author vkrava4
 * @since 0.0.1-SNAPSHOT
 */
@Configuration
public class KafkaProducerConfig {

    @Value(value = "${kafka.bootstrap.address}")
    private String bootstrapAddress;

    @Value(value = "${schema.registry.url.value}")
    private String schemaRegistryAddress;

    @Value(value = "${specific.avro.reader.value}")
    private boolean specificAvroReaderValue;


    @Bean
    public ProducerFactory<String, EmailData> producerFactory() {
        final Map<String, Object> kafkaConfigs = new HashMap<>();
//        Kafka
        kafkaConfigs.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        kafkaConfigs.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaConfigs.put(VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);

//        Schema Registry
        kafkaConfigs.put("schema.registry.url", schemaRegistryAddress);
        kafkaConfigs.put("specific.avro.reader", specificAvroReaderValue);

        return new DefaultKafkaProducerFactory<>(kafkaConfigs);
    }

    @Bean
    public KafkaTemplate<String, EmailData> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}

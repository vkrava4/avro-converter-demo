package com.vladkrava.avroconverterdemo.resource;

import static com.vladkrava.converter.http.AbstractAvroHttpMessageConverter.AVRO_JSON;
import static com.vladkrava.converter.http.AbstractAvroHttpMessageConverter.AVRO_XML;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vladkrava.avroconverterdemo.domain.EmailData;
import com.vladkrava.avroconverterdemo.service.EmailDataHandler;

/**
 * Resource class for demonstrating a work of Avro Converter library
 *
 * @author vkrava4
 * @since 0.0.1-SNAPSHOT
 */
@RestController
@RequestMapping("/user/handle")
public class EmailResource {

    private final EmailDataHandler emailDataHandler;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailResource.class);

    public EmailResource(final EmailDataHandler emailDataHandler) {
        this.emailDataHandler = emailDataHandler;
    }

    @PutMapping(produces = AVRO_XML, consumes = AVRO_XML)
    public ResponseEntity<EmailData> handleXmlEmailData(@RequestBody final EmailData data) {
        return ResponseEntity.ok(emailDataHandler.handle(data));
    }

    @PutMapping(produces = AVRO_JSON, consumes = AVRO_JSON)
    public ResponseEntity<EmailData> handleJsonEmailData(@RequestBody final EmailData data) {
        return ResponseEntity.ok(emailDataHandler.handle(data));
    }

    /**
     * This method demonstrates that {@code MappingJackson2HttpMessageConverter} can't serialize
     * Apache Avro object
     *
     * It will return HTTP 500 with the exception reason
     */
    @PutMapping(value = "/bad", consumes = "application/json", produces = "application/json")
    public ResponseEntity submitJsonEmail(@RequestBody final EmailData emailData) {
        LOGGER.info("This method can't handle Apache Avro object");
        return ResponseEntity.ok(emailData);
    }
}

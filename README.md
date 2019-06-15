# Avro Converter Demo

Demo project for Avro Converter Java library which enables conversion of Apache Avro object into popular data formats: JSON, XML, etc.

## Installing
* Execute Docker Compose command `docker-compose up` to start configured docker container. This step will take few minutes on executing it the first time
* Build Apache Maven project `mvn clean install` and run Spring Boot application `mvn spring-boot:run`
* All good? Execute `PUT` request to [/user/handle](http://localhost:8080/user/handle) with  `application/avro-json` or 
`application/avro-xml` Accept and Content-Type with `{"username": "test", "email": "test@test.com", "isSubscribed": false}` 
in order to check Avro Converter
* Check received messages on [Kafka Topics UI](http://localhost:3030/kafka-topics-ui/#/cluster/fast-data-dev/topic/n/user_data_processor/)
* Check registered Apache Avro Schema on [Schema Registry UI](http://localhost:3030/schema-registry-ui/#/cluster/fast-data-dev/schema/user_data_processor-value/version/1)
* Don't forget to install Avro Converter on your project

#### Useful information and resources
Feeling difficulties with understanding Apache Avro concepts or onboarding Avro Converter to your project?
Visit useful links which mentioned down below to get explicit information to all your questions:

 * [Avro Converter.](https://gitlab.com/vkrava4/avro-converter) Java library which enables conversion of Apache Avro object into popular data formats: JSON, XML, etc.
 * [Avro Converter Article.](https://gitlab.com/vkrava4-hands-on/avro-converter-demo) Quick overview on integration of Spring Boot, Apache Kafka and Avro Converter 
 * [Apache Avro Homepage.](https://avro.apache.org) The place where everything has started
 * [Apache Avro Getting Started Page.](https://avro.apache.org/docs/current/gettingstartedjava.html) A short guide for getting started with Apache Avro using Java


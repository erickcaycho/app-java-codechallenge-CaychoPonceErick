package com.yape.app.transaction.config;

import com.yape.app.transaction.model.entity.Transaction;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfigMain {
    @Value("${spring.kafka.bootstrap-servers}")
    private String boostrapServerMain;

    @Bean
    public ReactiveKafkaProducerTemplate<String, Transaction> reactiveKafkaProducerTemplate() {
        Map<String, Object> propertiesConfig = new HashMap<>();
        propertiesConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServerMain);
        propertiesConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        propertiesConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        propertiesConfig.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        propertiesConfig.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);

        SenderOptions<String, Transaction> senderOptions = SenderOptions.create(propertiesConfig);
        return new ReactiveKafkaProducerTemplate<>(senderOptions);
    }

    @Bean
    public ReactiveKafkaConsumerTemplate<String, Transaction> reactiveKafkaConsumerTemplate() {
        Map<String, Object> propertiesConfig = new HashMap<>();
        propertiesConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServerMain);
        propertiesConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propertiesConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                ErrorHandlingDeserializer.class.getName());
        propertiesConfig.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS,
                JsonDeserializer.class.getName());
        propertiesConfig.put(JsonDeserializer.VALUE_DEFAULT_TYPE, Transaction.class.getName());
        propertiesConfig.put(JsonDeserializer.TRUSTED_PACKAGES,
                "com.yape.app.antifraud.model.entity,com.yape.app.transaction.model.entity");

        ReceiverOptions<String, Transaction> receiverOptions = ReceiverOptions.create(propertiesConfig);
        return new ReactiveKafkaConsumerTemplate<>(receiverOptions);
    }

    @Bean
    public ConsumerFactory<String,Transaction> consumerFactory(){
        Map<String, Object> propertiesConfig = new HashMap<>();
        propertiesConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServerMain);
        propertiesConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propertiesConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        propertiesConfig.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        propertiesConfig.put(JsonDeserializer.VALUE_DEFAULT_TYPE,
                "com.yape.app.transaction.model.entity.Transaction");
        propertiesConfig.put(JsonDeserializer.TRUSTED_PACKAGES,
                "com.yape.app.transaction.model.entity");
        return new DefaultKafkaConsumerFactory<>(propertiesConfig);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Transaction> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Transaction> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}

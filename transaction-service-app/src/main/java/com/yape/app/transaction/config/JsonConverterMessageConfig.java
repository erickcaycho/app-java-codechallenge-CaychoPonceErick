package com.yape.app.transaction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.ByteArrayJsonMessageConverter;
import org.springframework.kafka.support.converter.JsonMessageConverter;

@Configuration
public class JsonConverterMessageConfig {
    @Bean
    public JsonMessageConverter jsonConverterMessage() {
        return new ByteArrayJsonMessageConverter();
    }
}

package br.com.jaxx.producer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.SimpleMessageConverter;

import java.util.List;

@Configuration
public class RabbitConfigSimple {

    @Bean
    public SimpleMessageConverter messageConverter() {
        SimpleMessageConverter converter = new SimpleMessageConverter();
        converter.setAllowedListPatterns(List.of("*"));
        return converter;
    }
}
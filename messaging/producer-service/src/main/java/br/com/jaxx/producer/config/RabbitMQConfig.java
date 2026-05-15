package br.com.jaxx.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXG_NAME_MARKETPLACE = "marketplace.topic";
    public static final String ROUTING_KEY_LOG = "product.topic.log";

    public static final String ROUTING_KEY_MARKETPLACE = "marketplace.topic";

    public static final String QUEUE_LOG = "product.log.topic";
    public static final String QUEUE_MARKETPLACE = "product.marketplace.topic";


    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXG_NAME_MARKETPLACE, false, false);
    }

    @Bean
    public Queue queueLog() {
        return new Queue(QUEUE_LOG);
    }

    @Bean
    public Queue queueMarketplace() {
        return new Queue(QUEUE_MARKETPLACE);
    }

    @Bean
    public Binding bindingLog() {
        return BindingBuilder
                .bind(queueLog())
                .to(topicExchange())
                .with(ROUTING_KEY_LOG);
    }

    @Bean
    public Binding bindingMarketplace() {
        return BindingBuilder
                .bind(queueMarketplace())
                .to(topicExchange())
                .with(ROUTING_KEY_MARKETPLACE);
    }



}

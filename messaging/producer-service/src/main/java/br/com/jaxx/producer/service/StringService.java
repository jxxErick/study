package br.com.jaxx.producer.service;

import br.com.jaxx.producer.rabbitmq.producers.StringProducers;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.jaxx.producer.config.RabbitMQConfig.EXG_NAME_MARKETPLACE;
import static br.com.jaxx.producer.config.RabbitMQConfig.ROUTING_KEY_LOG;


@Service
@Log4j2
public class StringService implements IStringService {

    @Autowired
    private StringProducers producers;

    @Override
    public void produce(String message) {
        log.info("m=produce stage=message received: " + message);
        producers.producerLogProduct(message);
        log.info("m=produce stage=message sent: " + message);
    }
}

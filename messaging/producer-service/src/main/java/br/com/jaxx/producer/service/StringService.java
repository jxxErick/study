package br.com.jaxx.producer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static br.com.jaxx.producer.config.RabbitMQConfig.EXG_NAME_MARKETPLACE;
import static br.com.jaxx.producer.config.RabbitMQConfig.ROUTING_KEY_LOG;


@Service
@Log4j2
@RequiredArgsConstructor
public class StringService implements IStringService {

    private final RabbitTemplate rabbitTemplate;


    @Override
    public void produce(String message) {
        log.info("m=produce stage=message received: " + message);
        rabbitTemplate.convertAndSend(EXG_NAME_MARKETPLACE, ROUTING_KEY_LOG, message);
        log.info("m=produce stage=message sent: " + message);
    }
}

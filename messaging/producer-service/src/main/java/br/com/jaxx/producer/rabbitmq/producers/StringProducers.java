package br.com.jaxx.producer.rabbitmq.producers;

import br.com.jaxx.producer.dtos.ProductDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.jaxx.producer.config.RabbitMQConfig.*;

@Component
@Log4j2
public class StringProducers {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void producerLogProduct(String message){
        log.info("m=producer stage=message received: {}", message);
        rabbitTemplate.convertAndSend(EXG_NAME_MARKETPLACE, ROUTING_KEY_LOG, message);
    }

    public void producerMarketplaceProduct(ProductDTO product){
        log.info("m=producerMarketplaceProduct stage=message received: {}", product);
        rabbitTemplate.convertAndSend(EXG_NAME_MARKETPLACE, ROUTING_KEY_MARKETPLACE, product);
    }
}

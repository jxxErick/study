package br.com.jaxx.producer.rabbitmq.consumers;

import br.com.jaxx.producer.config.RabbitMQConfig;
import br.com.jaxx.producer.dtos.ProductDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static br.com.jaxx.producer.config.RabbitMQConfig.*;

@Component
@Log4j2
public class StringConsumers {

    @RabbitListener(queues = QUEUE_LOG)
    public void consumerLog(String message){
        log.info("LOG: {}", message);
    }

    @RabbitListener(queues = QUEUE_MARKETPLACE)
    public void consumerMarketplace(ProductDTO message){
        log.info("MARKETPLACE: {}", message.toString());
    }

}

package br.com.jaxx.producer.rabbitmq.consumers;

import br.com.jaxx.producer.config.RabbitMQConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static br.com.jaxx.producer.config.RabbitMQConfig.QUEUE_NAME_PRODUCT_LOG;

@Component
@Log4j2
public class StringConsumers {

    @RabbitListener(queues = {QUEUE_NAME_PRODUCT_LOG})
    public void consumer(String message){
       log.info("m=consumer stage=message receive: {}", message);
    }
}

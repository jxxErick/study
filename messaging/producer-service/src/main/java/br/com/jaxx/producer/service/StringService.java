package br.com.jaxx.producer.service;

import br.com.jaxx.producer.rabbitmq.producers.StringProducers;
import br.com.jaxx.producer.service.interfaces.IStringService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

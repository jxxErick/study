package br.com.jaxx.producer.service;

import br.com.jaxx.producer.dtos.ProductDTO;
import br.com.jaxx.producer.rabbitmq.producers.StringProducers;
import br.com.jaxx.producer.service.interfaces.IProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductService implements IProductService {

    @Autowired
    private StringProducers stringProducers;

    @Override
    public void creatProduct(ProductDTO product) {
        log.info("m=creatProduct stage=product created: " + product);
        stringProducers.producerMarketplaceProduct(product);
    }
}

package br.com.jaxx.producer.controller;

import br.com.jaxx.producer.dtos.ProductDTO;
import br.com.jaxx.producer.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.jaxx.producer.service.StringService;

@RestController
@RequestMapping("/produces")
@RequiredArgsConstructor
public class StringController {

    private final StringService service;
    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<String> producer(
            @RequestBody String message
    ) {
        service.produce(message);
        return ResponseEntity
                .ok()
                .body("Message send");
    }

    @PostMapping("/product")
    public ResponseEntity<String> producerProduct(
            @RequestBody ProductDTO message

    ) {
        productService.creatProduct(message);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Message send");
    }
}

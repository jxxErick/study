package br.com.jaxx.producer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.jaxx.producer.service.StringService;

@RestController
@RequestMapping("/produces")
@RequiredArgsConstructor
public class StringController {

    private final StringService service;

    @PostMapping()
    public ResponseEntity<String> producer(
            @RequestBody String message
    ) {
        service.produce(message);
        return ResponseEntity
                .ok()
                .body("Message send");
    }
}

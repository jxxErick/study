# RabbitMQ com Java (Spring Boot)

### O que é
RabbitMQ é um message broker que implementa o protocolo AMQP, permitindo comunicação assíncrona entre sistemas.

Com Spring Boot, usamos o Spring AMQP para facilitar a integração.

---

## Dependência (Maven)

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

---

## application.yml

```yaml
spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
```

---

## Estrutura básica

Producer → Exchange → Queue → Consumer

---

## Configuração (Exchange, Queue, Binding)

```java
@Configuration
public class RabbitConfig {

    public static final String QUEUE = "fila.teste";
    public static final String EXCHANGE = "exchange.teste";
    public static final String ROUTING_KEY = "routing.key";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE, true); // durable
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }
}
```

---

## Producer (Enviando mensagem)

```java
@Service
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String message) {
        rabbitTemplate.convertAndSend(
            RabbitConfig.EXCHANGE,
            RabbitConfig.ROUTING_KEY,
            message
        );

        System.out.println("Mensagem enviada: " + message);
    }
}
```

---

## Consumer (Recebendo mensagem)

```java
@Component
public class MessageConsumer {

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void receive(String message) {
        System.out.println("Mensagem recebida: " + message);
    }
}
```

---

## Controller (para testar)

```java
@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private MessageProducer producer;

    @GetMapping("/send")
    public String send(@RequestParam String msg) {
        producer.send(msg);
        return "Mensagem enviada!";
    }
}
```

---

## Tipos de Exchange (uso prático)

### Direct (mais comum)
- Usa routing key exata
- Ideal para filas específicas

### Fanout
- Broadcast (envia para todas as filas)

```java
new FanoutExchange("exchange.fanout");
```

### Topic
- Usa padrões (wildcards)

```text
user.*
order.#
```

---

## Ack (Confirmação)

Por padrão, o Spring faz auto-ack.

Para controle manual:

```java
@RabbitListener(queues = "fila.teste")
public void receive(Message message, Channel channel) throws Exception {
    try {
        // processa
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    } catch (Exception e) {
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
    }
}
```

---

## Prefetch (controle de carga)

```yaml
spring:
  rabbitmq:
    listener:
      simple:
        prefetch: 1
```

👉 Processa 1 mensagem por vez por consumer

---

## Dead Letter Queue (DLQ)

```java
@Bean
public Queue queue() {
    Map<String, Object> args = new HashMap<>();
    args.put("x-dead-letter-exchange", "dlx.exchange");

    return new Queue("fila.teste", true, false, false, args);
}
```

---

## Fluxo real

1. Controller chama Producer
2. Producer envia para Exchange
3. Exchange roteia para Queue
4. Consumer escuta fila
5. Processa mensagem
6. Ack confirma processamento

---

## Quando usar

- Processamento assíncrono
- Filas de pedidos (ex: e-commerce)
- Envio de e-mails
- Integração entre microsserviços

---

## Boas práticas

- Usar filas duráveis
- Mensagens persistentes
- Tratar erros (retry / DLQ)
- Usar prefetch para controle
- Monitorar filas (RabbitMQ UI)

---

## Resumo rápido

- RabbitMQ = broker de mensagens
- Usa AMQP
- Exchange decide destino
- Queue armazena
- Consumer processa
- Ack garante confiabilidade
- Spring Boot facilita tudo com @RabbitListener
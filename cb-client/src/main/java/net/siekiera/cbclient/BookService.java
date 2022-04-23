package net.siekiera.cbclient;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
public class BookService {
    private static final Logger LOG = LoggerFactory.getLogger(BookService.class);

    private final RestTemplate restTemplate;
    private final CircuitBreaker circuitBreaker;
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    public BookService(CircuitBreakerFactory circuitBreakerFactory, CircuitBreakerRegistry circuitBreakerRegistry) {
        this.restTemplate = new RestTemplate();
        this.circuitBreaker = circuitBreakerFactory.create("cb1");
        this.circuitBreakerRegistry = circuitBreakerRegistry;
        this.circuitBreakerRegistry.getEventPublisher().onEvent(ev -> LOG.info(ev.getEventType().toString()));
    }

    public String slow() {
        return circuitBreaker.run(() -> restTemplate.getForObject("http://localhost:8080/slow", String.class), throwable -> "fallback");
    }
}

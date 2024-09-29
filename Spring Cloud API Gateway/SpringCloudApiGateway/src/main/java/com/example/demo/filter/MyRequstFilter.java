package com.example.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class MyRequstFilter implements GlobalFilter, Ordered {
    
    private static final Logger LOG = LoggerFactory.getLogger(MyRequstFilter.class);

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // pre-processing logic
        LOG.info("URL => " + exchange.getRequest().getURI());
        LOG.info("METHOD => " + exchange.getRequest().getMethod());
        exchange.getRequest().getHeaders().forEach((name, values)->{
            values.forEach(value -> LOG.info("HEADER: {} => {}", name, value));
        });
        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            // post-processing logic
            // access response details if needed
        }));
    }
    
}
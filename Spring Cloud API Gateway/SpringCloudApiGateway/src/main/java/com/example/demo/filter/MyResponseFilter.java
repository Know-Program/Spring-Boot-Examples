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
public class MyResponseFilter implements GlobalFilter, Ordered {

    private static final Logger LOG = LoggerFactory.getLogger(MyResponseFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            LOG.info("FROM RESPONSE FILTER");
            exchange.getResponse().getHeaders().forEach((name, values) -> {
                values.forEach(value -> LOG.info("HEADER: {} => {}", name, value));
            });
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }

}

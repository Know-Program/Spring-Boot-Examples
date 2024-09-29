package com.example.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class MyRoutingFilter extends AbstractGatewayFilterFactory<MyRoutingFilter.Config> {

    private static final Logger LOG = LoggerFactory.getLogger(MyRoutingFilter.class);

    public MyRoutingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            LOG.info("FROM ROUTING....");
            return chain.filter(exchange);
        };
    }

    public static class Config {
        // Configuration properties
    }

}

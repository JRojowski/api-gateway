package io.github.jrojowski.apigateway;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/food-service/**")
                        .filters(f -> f.rewritePath("/food-service/(?<segment>.*)", "/${segment}"))
                        .uri("lb://food-service"))
                .route(p -> p.path("/calories-service/**")
                        .filters(f -> f.rewritePath("/calories-service/(?<segment>.*)", "/${segment}"))
                        .uri("lb://calories-service"))
                .build();
    }
}

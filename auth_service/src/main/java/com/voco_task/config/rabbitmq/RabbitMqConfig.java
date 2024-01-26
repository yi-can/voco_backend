package com.voco_task.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {


    //AUTHDAN RESTAURANT'A Restaurant
    private String exchangeRestaurant = "exchangeRestaurant";
    private String queueRestaurant = "queueRestaurant";
    private String createRestaurantBindingKey = "createRestaurantBindingKey";

    @Bean
    Queue queueRestaurant(){
        return new Queue(queueRestaurant);
    }
    @Bean
    DirectExchange exchangeRestaurant(){
        return new DirectExchange(exchangeRestaurant);
    }
    @Bean
    public Binding restaurantBindingKey(final Queue queueRestaurant, final DirectExchange exchangeRestaurant){
        return BindingBuilder.bind(queueRestaurant).to(exchangeRestaurant).with(createRestaurantBindingKey);
    }

}



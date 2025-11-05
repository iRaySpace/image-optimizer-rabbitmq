package com.irayspacii.imageoptimizerapi;

import org.slf4j.Logger;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QueueProducer {

    private final static String EXCHANGE = "image-optimizer-exchange";
    private final static String QUEUE = "image-optimizer-queue";

    @Bean
    public TopicExchange topicExchange() {
        System.out.println("Creating a TopicExchange: " + EXCHANGE);
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue queue() {
        System.out.println("Creating a queue: " + QUEUE);
        return new Queue(QUEUE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("image.uploaded");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(QueueProducer.EXCHANGE);
        return rabbitTemplate;
    }
}

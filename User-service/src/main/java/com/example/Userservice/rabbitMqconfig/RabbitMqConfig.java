package com.example.Userservice.rabbitMqconfig;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${rabbitmq.fetch.tasks.queue}")
    private String fetchTasksQueue;
    @Value("${rabbitmq.fetch.tasks.routing-key}")
    private String routingKeyFetchTasks;
    @Value("${rabbitmq.deleted.queue}")
    private String deletedQueue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.deleted.routing-key}")
    private String routingKeyDeletion;
    @Value("${rabbitmq.response.tasks.queue}")
    private String responseQueue;
    @Value("${rabbitmq.response.routing-key}")
    private String routingKeyResponse;

    @Bean
    public Queue Queue(){
        return  new Queue(deletedQueue);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(Queue())
                .to(exchange())
                .with(routingKeyDeletion);
    }
    @Bean
    public Queue fetchTasksQueue() {
        return new Queue(fetchTasksQueue,false);
    }
    @Bean
    public Binding bindingFetchTasks() {
        return BindingBuilder.bind(fetchTasksQueue())
                .to(exchange()).with(routingKeyFetchTasks);
    }
    @Bean
    public Queue responseQueue(){return new Queue(responseQueue);}
    @Bean
    public Binding bindingResponse() {
        return BindingBuilder.bind(responseQueue())
                .to(exchange())
                .with(routingKeyResponse);
    }
    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter jsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        return rabbitTemplate;
    }

}

package com.example.Taskservice.rabbitListener;

import com.example.Taskservice.entities.Task;
import com.example.Taskservice.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;


@AllArgsConstructor
@Component
public class TaskListener {

      final TaskRepository taskRepository;
      final RabbitTemplate rabbitTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskListener.class);
    @Value("${rabbitmq.response.routing-key}")
    private String routingKeyResponse;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Transactional
    @RabbitListener(queues = "${rabbitmq.deleted.queue}")
    public void receiveMessage(Long id) {
        LOGGER.info(String.format("received message -> %s", id));
        // Logic to delete tasks associated with the userId
        taskRepository.deleteTasksByUserId(id);
    }
  @Transactional
    @RabbitListener(queues = "${rabbitmq.fetch.tasks.queue}")
    public void onFetchTasksRequest(Long userId) {
        List<Task> tasks = taskRepository.getTasksByUserId(userId);
        rabbitTemplate.convertAndSend(exchange,routingKeyResponse,tasks);

        // In a real scenario, send these tasks back to the User Microservice or update a shared data store.
        LOGGER.info("Received tasks for user {}: {}", userId, tasks);
    }


}

package com.example.Userservice.rabbitListner;

import com.example.Userservice.model.Task;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Component
public class UserListener {
    @Transactional
    @RabbitListener(queues = "${rabbitmq.response.tasks.queue}")
    public void getTasks(List<Task> tasks){
        tasks.forEach(System.out::println);
    }

}

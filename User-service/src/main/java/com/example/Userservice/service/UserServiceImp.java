package com.example.Userservice.service;

import com.example.Userservice.entities.User;
import com.example.Userservice.entities.dto.UserRequestDto;
import com.example.Userservice.entities.dto.UserResponseDto;
import com.example.Userservice.repository.UserRepository;
import com.example.Userservice.utils.Mapping;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImp implements UserService {
    private static final Logger LOGGER
            = LoggerFactory.getLogger(UserServiceImp.class) ;

    private final UserRepository userRepository;

    private final RabbitTemplate rabbitTemplate;


    @Value("${rabbitmq.fetch.tasks.routing-key}")
    private String routingKeyFetchTasks;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.deleted.routing-key}")
    private String routingKeyDeletion;
    public UserServiceImp(UserRepository userRepository,RabbitTemplate rabbitTemplate) {
        this.userRepository = userRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(Mapping::mapToUserResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userDto) {
        User user = Mapping.mapToUserEntity(userDto);
        return Mapping.mapToUserResponseDto(userRepository.save(user));
    }

    @Transactional
    @Override
    public UserResponseDto getUserById(Long id) throws EntityNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        // Asynchronously request tasks for the user
        System.out.println(id);
        rabbitTemplate.convertAndSend(exchange, routingKeyFetchTasks, id);
        return Mapping.mapToUserResponseDto(user);
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        // Delete user logic
       try{
           LOGGER.info(String.format("Message sent -> %s , %s , %s",id,exchange,routingKeyDeletion));
           rabbitTemplate.convertAndSend(exchange, routingKeyDeletion, id);
           userRepository.deleteById(id);
       } catch (Exception e) {
           e.printStackTrace();
       }


    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userDto) throws EntityNotFoundException{
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userDto.getId()));

        // Update user fields
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());

        User updatedUser = userRepository.save(user);
        return Mapping.mapToUserResponseDto(updatedUser);
    }
}
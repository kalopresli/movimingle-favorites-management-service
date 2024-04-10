package com.backend.favoritesmanagementservice.service;

import com.backend.favoritesmanagementservice.dto.UserFavoriteMoviesMessage;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageSenderService {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend("myQueue", message);
    }

    public void sendUserFavoriteMovies(UserFavoriteMoviesMessage message) {
        rabbitTemplate.convertAndSend("favoritesToVotingQueue", message);
    }
}


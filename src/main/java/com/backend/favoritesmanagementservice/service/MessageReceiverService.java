package com.backend.favoritesmanagementservice.service;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
@Service
public class MessageReceiverService {

    private static final Logger logger = LoggerFactory.getLogger(MessageReceiverService.class);
    UserFavoriteService userFavoriteService;


//    public void receivePartyCreationTrigger (String message){
//        userFavoriteService.sendAllUserFavoriteMovies(Long.parseLong(message));
//    }

    @RabbitListener(queues = "votingToFavoritesQueue")
    public void receivePartyCreationTrigger(String message) {
        try {
            Long userId = Long.parseLong(message);// parse userId from the message
            userFavoriteService.sendAllUserFavoriteMovies(userId);// Attempt to fetch and send user favorites
        } catch (IllegalArgumentException e) {
            logger.error("Unable to process message due to error: {}", e.getMessage());
            // Handle the error appropriately, e.g., send an error message back, log it, etc.
        } catch (Exception e) {
            // Handle unexpected exceptions
            logger.error("Unexpected error while processing message: {}", e.getMessage());
        }
    }
}

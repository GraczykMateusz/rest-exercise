package dev.graczykmateusz.restexercise.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;

@JsonDeserialize(using = UserDeserializer.class)
public record User(Long id, String name, String type, String avatarUrl,
                   LocalDateTime createdAt, double calculations) {
}

package dev.graczykmateusz.restexercise.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.Instant;

@JsonDeserialize(using = UserDeserializer.class)
record UserData(Long id, String login, String name,
                String type, String avatarUrl, Instant createdAt,
                double calculations) {
}

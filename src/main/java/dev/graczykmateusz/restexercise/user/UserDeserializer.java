package dev.graczykmateusz.restexercise.user;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.graczykmateusz.restexercise.user.exception.IllegalCalculationsException;

import java.io.IOException;
import java.time.LocalDateTime;

class UserDeserializer extends JsonDeserializer<User> {

    @Override
    public User deserialize(JsonParser jp, DeserializationContext dc) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
        JsonNode node = jp.getCodec().readTree(jp);

        Long id = node.get("id").asLong();
        String name = node.get("name").asText();
        String type = node.get("type").asText();
        String avatarUrl = node.get("avatar_url").asText();
        LocalDateTime createdAt = objectMapper.convertValue(node.get("created_at"), LocalDateTime.class);

        int followers = node.get("followers").intValue();
        int publicRepos = node.get("public_repos").intValue();

        if (followers == 0) throw new IllegalCalculationsException();
        double calculations = 6 / (double) followers * (2 + (double) publicRepos);

        return new User(id, name, type, avatarUrl, createdAt, calculations);
    }
}

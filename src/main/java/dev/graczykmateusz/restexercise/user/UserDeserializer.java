package dev.graczykmateusz.restexercise.user;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.graczykmateusz.restexercise.user.exception.ZeroFollowersException;
import java.io.IOException;
import java.time.Instant;

class UserDeserializer extends JsonDeserializer<UserData> {

    @Override
    public UserData deserialize(JsonParser jp, DeserializationContext dc) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
        JsonNode node = jp.getCodec().readTree(jp);

        Long id = node.get("id").asLong();
        String login = node.get("login").asText();
        String name = node.get("name").asText();
        String type = node.get("type").asText();
        String avatarUrl = node.get("avatar_url").asText();
        Instant createdAt = objectMapper.convertValue(node.get("created_at"), Instant.class);

        int followers = node.get("followers").intValue();
        int publicRepos = node.get("public_repos").intValue();

        if (followers == 0) {
            throw new ZeroFollowersException(
                "Calculation cannot be performed because the number of followers is zero!");
        }
        double calculations = 6 / (double) followers * (2 + (double) publicRepos);

        return new UserData(
            id, login, name,
            type, avatarUrl, createdAt,
            calculations);
    }
}

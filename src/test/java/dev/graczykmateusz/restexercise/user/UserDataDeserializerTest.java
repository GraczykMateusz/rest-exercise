package dev.graczykmateusz.restexercise.user;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.graczykmateusz.restexercise.user.exception.ZeroFollowersException;
import dev.graczykmateusz.restexercise.utils.GithubClientResponseProvider;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserDataDeserializerTest {

    private UserDeserializer underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserDeserializer();
    }

    @Test
    void shouldDeserializeJsonUser() throws IOException, JSONException {
        String userNonZeroFollowersResponse = GithubClientResponseProvider.getUserNonZeroFollowersResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonParser jp = objectMapper.getFactory().createParser(userNonZeroFollowersResponse);

        UserData actual = underTest.deserialize(jp, null);

        UserData expected = new UserData(
                43554417L,
                "GraczykMateusz",
                "Mateusz Graczyk",
                "User",
                "https://avatars.githubusercontent.com/u/43554417?v=4",
                Instant.parse("2018-09-24T20:41:09Z"),
                38.0);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldThrowIllegalCalculationsExceptionWhenFollowersCountEqualsZero() throws IOException, JSONException {
        String userZeroFollowersResponse = GithubClientResponseProvider.getUserZeroFollowersResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonParser jp = objectMapper.getFactory().createParser(userZeroFollowersResponse);

        assertThatThrownBy(() -> underTest.deserialize(jp, null))
                .isInstanceOf(ZeroFollowersException.class)
                .hasMessage("Calculation cannot be performed because the number of followers is zero!");
    }
}
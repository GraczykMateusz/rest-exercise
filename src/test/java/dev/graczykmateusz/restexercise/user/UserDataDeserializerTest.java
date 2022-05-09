package dev.graczykmateusz.restexercise.user;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.graczykmateusz.restexercise.user.exception.IllegalCalculationsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
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
    void shouldDeserializeJsonUser() throws IOException {
        //given
        File file = new File("src/test/resources/api-github-response/user-response.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonParser jp = objectMapper.getFactory().createParser(file);

        //when
        UserData actual = underTest.deserialize(jp, null);

        //then
        Long id = 43554417L;
        String login = "GraczykMateusz";
        String name = "Mateusz Graczyk";
        String type = "User";
        String avatarUrl = "https://avatars.githubusercontent.com/u/43554417?v=4";
        Instant createdAt = Instant.parse("2018-09-24T20:41:09Z");
        double calculations = 38.0;

        UserData expected = new UserData(
                id, login, name,
                type, avatarUrl, createdAt,
                calculations);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldThrowIllegalCalculationsExceptionWhenFollowersCountEqualsZero() throws IOException {
        //given
        File file = new File("src/test/resources/api-github-response/user-zero-followers-response.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonParser jp = objectMapper.getFactory().createParser(file);

        //when
        //then
        assertThatThrownBy(() -> underTest.deserialize(jp, null))
                .isInstanceOf(IllegalCalculationsException.class)
                .hasMessage("Cannot divide by zero!");
    }

}
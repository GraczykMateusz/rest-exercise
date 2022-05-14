package dev.graczykmateusz.restexercise.user;

import dev.graczykmateusz.restexercise.user.exception.GithubClientException;
import dev.graczykmateusz.restexercise.user.exception.UserNotFoundException;
import dev.graczykmateusz.restexercise.user.statistics.event.UserStatisticEventPublisher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private RestTemplate mockRestTemplate;

    @Mock
    private UserStatisticEventPublisher mockUserStatisticEventPublisher;

    @InjectMocks
    private UserService underTest;

    @Test
    void shouldReturnUserWhenLoginIsCorrect() {
        UserData expected = new UserData(
                1L,
                "GraczykMateusz",
                "Mateusz Graczyk",
                "User",
                "https://avatars.githubusercontent.com/u/43554417?v=4",
                Instant.parse("2018-09-24T20:41:09Z"),
                38
        );

        when(mockRestTemplate.getForObject(any(URI.class), eq(UserData.class)))
                .thenReturn(expected);

        UserData actual = underTest.getUserData("GraczykMateusz");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldInvokeUserStatisticEventPublisherPublishEventMethod() {
        underTest.getUserData("GraczykMateusz");

        then(mockUserStatisticEventPublisher)
                .should(times(1))
                .publishEvent("GraczykMateusz");
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenGithubUserNotFound() {
        when(mockRestTemplate.getForObject(any(URI.class), eq(UserData.class)))
                .thenThrow(HttpClientErrorException.NotFound.class);

        assertThatThrownBy(() -> underTest.getUserData("UnknownUser"))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User with login \"UnknownUser\" not found!");
    }

    @Test
    void shouldThrowGithubClientException() {
        when(mockRestTemplate.getForObject(any(URI.class), eq(UserData.class)))
                .thenThrow(HttpClientErrorException.class);

        assertThatThrownBy(() -> underTest.getUserData(null))
                .isInstanceOf(GithubClientException.class);
    }
}
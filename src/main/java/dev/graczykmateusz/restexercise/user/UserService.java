package dev.graczykmateusz.restexercise.user;

import dev.graczykmateusz.restexercise.user.exception.UserNotFoundException;
import dev.graczykmateusz.restexercise.user.statistics.event.UserStatisticEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RestTemplate restTemplate;
    private final UserStatisticEventPublisher userStatisticEventPublisher;

    @Value("${url.api.github.users}")
    private String urlApiGithubUsers;

    UserData getUserData(String login) {
        userStatisticEventPublisher.publishEvent(login);
        URI uri = URI.create(urlApiGithubUsers + login);
        try {
            return restTemplate.getForObject(uri, UserData.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException(login);
        }
    }
}

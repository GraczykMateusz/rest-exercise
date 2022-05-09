package dev.graczykmateusz.restexercise.user;

import dev.graczykmateusz.restexercise.user.exception.UserNotFoundException;
import dev.graczykmateusz.restexercise.user.statistics.event.UserStatisticEventPublisher;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@RequiredArgsConstructor
class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final RestTemplate restTemplate;
    private final UserStatisticEventPublisher userStatisticEventPublisher;

    @Value("${api.github.users.url}")
    private String apiGithubUsersUrl;

    UserData getUserData(String login) {
        try {
            URI uri = URI.create(apiGithubUsersUrl + login);
            return restTemplate.getForObject(uri, UserData.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new UserNotFoundException(login);
            }
            throw e;
        }
    }

    void updateUserLoginStatistic(String login) {
        userStatisticEventPublisher.publishEvent(login);
    }
}

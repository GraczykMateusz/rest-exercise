package dev.graczykmateusz.restexercise.user;

import dev.graczykmateusz.restexercise.user.exception.GithubClientException;
import dev.graczykmateusz.restexercise.user.exception.UserNotFoundException;
import dev.graczykmateusz.restexercise.user.statistics.event.UserStatisticEventPublisher;
import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
class UserService {

    private final RestTemplate restTemplate;
    private final UserStatisticEventPublisher userStatisticEventPublisher;
    private final String urlApiGithubUsers;

    UserService(RestTemplate restTemplate,
                UserStatisticEventPublisher userStatisticEventPublisher,
                @Value("${url.api.github.users}") String urlApiGithubUsers) {
        this.restTemplate = restTemplate;
        this.userStatisticEventPublisher = userStatisticEventPublisher;
        this.urlApiGithubUsers = urlApiGithubUsers;
    }

    UserData getUserData(String login) {
        userStatisticEventPublisher.publishEvent(login);
        URI uri = URI.create(urlApiGithubUsers + login);
        try {
            return restTemplate.getForObject(uri, UserData.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException(login);
        } catch (HttpClientErrorException e) {
            throw new GithubClientException(e);
        }
    }
}

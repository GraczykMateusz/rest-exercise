package dev.graczykmateusz.restexercise.user;

import dev.graczykmateusz.restexercise.user.exception.UserNotFoundException;
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

    @Value("${api.github.users.url}")
    private String apiGithubUsersUrl;

    public User getUserData(String login) {
        URI uri = URI.create(apiGithubUsersUrl + login);
        try {
            return restTemplate.getForObject(uri, User.class);
        } catch (HttpClientErrorException e) {
            throw new UserNotFoundException(login);
        }
    }
}

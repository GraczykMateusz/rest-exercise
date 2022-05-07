package dev.graczykmateusz.restexercise.user;

import dev.graczykmateusz.restexercise.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RestTemplate restTemplate;
    private final UserLoginStatisticRepository userLoginStatisticRepository;

    @Value("${api.github.users.url}")
    private String apiGithubUsersUrl;

    public User getUserData(String login) {
        try {
            URI uri = URI.create(apiGithubUsersUrl + login);
            return restTemplate.getForObject(uri, User.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new UserNotFoundException(login);
            }
            throw e;
        }
    }

    public void updateUserLoginStatistics(String login) {
        UserLoginStatistic userLoginStatistic = userLoginStatisticRepository.getByLogin(login);
        if (userLoginStatistic != null) {
            int currentRequestCount = userLoginStatistic.getRequestCount();
            userLoginStatistic.setRequestCount(++currentRequestCount);
            userLoginStatisticRepository.save(userLoginStatistic);
        } else  {
            userLoginStatisticRepository.save(new UserLoginStatistic(login));
        }
    }
}

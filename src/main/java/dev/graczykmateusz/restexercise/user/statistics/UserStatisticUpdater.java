package dev.graczykmateusz.restexercise.user.statistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public record UserStatisticUpdater(UserStatisticRepository userStatisticRepository) {

    private static final Logger LOG = LoggerFactory.getLogger(UserStatisticUpdater.class);

    public void updateLoginRequestCount(String login) {
        LOG.info("Starting update login request count for: \"{}\"", login);

        UserStatistic userStatistic = userStatisticRepository.getByLogin(login);
        if (userStatistic == null) {
            userStatistic = new UserStatistic(login);
            LOG.info("User with login: \"{}\" not found. Created new record in database.", login);
        } else {
            incrementRequestCount(userStatistic);
        }
        userStatisticRepository.save(userStatistic);

        LOG.info("Request login count for: \"{}\" was updated successfully", login);
    }

    private void incrementRequestCount(UserStatistic userStatistic) {
        int currentRequestCount = userStatistic.getRequestCount();
        userStatistic.setRequestCount(++currentRequestCount);
    }
}

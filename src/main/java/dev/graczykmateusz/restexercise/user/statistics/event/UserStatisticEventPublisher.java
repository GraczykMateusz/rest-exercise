package dev.graczykmateusz.restexercise.user.statistics.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public record UserStatisticEventPublisher(ApplicationEventPublisher applicationEventPublisher) {

    private static final Logger LOG = LoggerFactory.getLogger(UserStatisticEventPublisher.class);

    public void publishEvent(String login) {
        LOG.info("Publishing user statistic event: \"{}\"", login);
        UserStatisticEvent userStatisticEvent = new UserStatisticEvent(this, login);
        applicationEventPublisher.publishEvent(userStatisticEvent);
    }
}

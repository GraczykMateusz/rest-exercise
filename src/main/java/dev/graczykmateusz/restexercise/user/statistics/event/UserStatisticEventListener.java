package dev.graczykmateusz.restexercise.user.statistics.event;

import dev.graczykmateusz.restexercise.user.statistics.UserStatisticUpdater;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserStatisticEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(UserStatisticEventListener.class);

    private final UserStatisticUpdater userStatisticUpdater;

    @Async
    @EventListener
    public void listen(UserStatisticEvent event) {
        LOG.info("Received user statistic event: \"{}\"", event.getLogin());
        userStatisticUpdater.updateLoginRequestCount(event.getLogin());
    }
}

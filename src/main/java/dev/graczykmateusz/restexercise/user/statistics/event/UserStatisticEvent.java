package dev.graczykmateusz.restexercise.user.statistics.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
class UserStatisticEvent extends ApplicationEvent {

    private final String login;

    UserStatisticEvent(Object source, String login) {
        super(source);
        this.login = login;
    }
}

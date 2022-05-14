package dev.graczykmateusz.restexercise.user.statistics.event;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class UserStatisticEventPublisherTest {

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private UserStatisticEventPublisher underTest;

    @Test
    void shouldPublishEvent() {
        underTest.publishEvent("GraczykMateusz");

        ArgumentCaptor<UserStatisticEvent> userStatisticEventCaptor = ArgumentCaptor.forClass(UserStatisticEvent.class);
        then(applicationEventPublisher)
                .should(times(1))
                .publishEvent(userStatisticEventCaptor.capture());

        UserStatisticEvent actual = userStatisticEventCaptor.getValue();

        assertThat(actual.getLogin()).isEqualTo("GraczykMateusz");
    }
}
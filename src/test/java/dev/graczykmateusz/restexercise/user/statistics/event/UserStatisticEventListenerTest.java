package dev.graczykmateusz.restexercise.user.statistics.event;

import dev.graczykmateusz.restexercise.user.statistics.UserStatisticUpdater;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class UserStatisticEventListenerTest {

    @Mock
    private UserStatisticUpdater userStatisticUpdater;

    @InjectMocks
    private UserStatisticEventListener underTest;

    @Test
    void shouldInvokeUserStatisticUpdaterUpdateLoginRequestCountMethod() {
        underTest.listen(new UserStatisticEvent(this, "GraczykMateusz"));

        ArgumentCaptor<String> loginCaptor = ArgumentCaptor.forClass(String.class);
        then(userStatisticUpdater)
                .should(times(1))
                .updateLoginRequestCount(loginCaptor.capture());

        String actual = loginCaptor.getValue();
        assertThat(actual).isEqualTo("GraczykMateusz");
    }
}
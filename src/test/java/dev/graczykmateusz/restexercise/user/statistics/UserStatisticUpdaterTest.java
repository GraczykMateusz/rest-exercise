package dev.graczykmateusz.restexercise.user.statistics;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserStatisticUpdaterTest {

    @Mock
    private UserStatisticRepository userStatisticRepository;

    @InjectMocks
    private UserStatisticUpdater underTest;

    @Test
    void shouldIncrementAndUpdateLoginRequestCount() {
        UserStatistic userStatistic = new UserStatistic();
        userStatistic.setRequestCount(6);
        when(userStatisticRepository.getByLogin("GraczykMateusz"))
                .thenReturn(userStatistic);

        underTest.updateLoginRequestCount("GraczykMateusz");

        ArgumentCaptor<UserStatistic> userStatisticCaptor = ArgumentCaptor.forClass(UserStatistic.class);
        then(userStatisticRepository)
                .should(times(1))
                .save(userStatisticCaptor.capture());

        UserStatistic actual = userStatisticCaptor.getValue();

        assertThat(actual.getRequestCount()).isEqualTo(7);
    }

    @Test
    void shouldCreateNewRecordInDatabase() {
        underTest.updateLoginRequestCount("GraczykMateusz");

        ArgumentCaptor<UserStatistic> userStatisticCaptor = ArgumentCaptor.forClass(UserStatistic.class);
        then(userStatisticRepository)
                .should(times(1))
                .save(userStatisticCaptor.capture());

        UserStatistic actual = userStatisticCaptor.getValue();

        assertThat(actual.getRequestCount()).isEqualTo(1);
    }
}
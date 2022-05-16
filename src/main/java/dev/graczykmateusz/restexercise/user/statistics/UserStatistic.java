package dev.graczykmateusz.restexercise.user.statistics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "USERS_STATISTICS")
class UserStatistic {

    @Id
    @SequenceGenerator(
        name = "USERS_STATISTICS_SEQUENCE",
        sequenceName = "USERS_STATISTICS_SEQUENCE",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_STATISTICS_SEQUENCE")
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "LOGIN", nullable = false)
    private String login;

    @Setter
    @Getter
    @Column(name = "REQUEST_COUNT", nullable = false)
    private int requestCount = 1;

    public UserStatistic(String login) {
        this.login = login;
    }
}

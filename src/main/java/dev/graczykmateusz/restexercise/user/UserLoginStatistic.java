package dev.graczykmateusz.restexercise.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "USERS_STATISTICS")
@DynamicInsert
@NoArgsConstructor
public class UserLoginStatistic {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "LOGIN", nullable = false)
    private String login;

    @Setter
    @Getter
    @Column(name = "REQUEST_COUNT", nullable = false)
    private int requestCount = 1;

    public UserLoginStatistic(String login) {
        this.login = login;
    }
}

package dev.graczykmateusz.restexercise.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginStatisticRepository extends JpaRepository<UserLoginStatistic, Long> {

    UserLoginStatistic getByLogin(String login);
}

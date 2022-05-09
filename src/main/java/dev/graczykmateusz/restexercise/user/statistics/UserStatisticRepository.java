package dev.graczykmateusz.restexercise.user.statistics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserStatisticRepository extends JpaRepository<UserStatistic, Long> {

    UserStatistic getByLogin(String login);
}

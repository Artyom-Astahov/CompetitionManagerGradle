package by.artem.spring.database.repository;


import by.artem.spring.database.entity.CompetitionCatalog;
import by.artem.spring.database.entity.User;
import by.artem.spring.database.entity.UserInfo;
import net.bytebuddy.TypeCache;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>,
        FilterUserRepository, QuerydslPredicateExecutor<User>{

    Optional<User> findUserByLogin(String login);

    @Query("select u from User u " +
            "where u.role = 'ATHLETE'")
    List<User> findAllByRoleAthlete();

}

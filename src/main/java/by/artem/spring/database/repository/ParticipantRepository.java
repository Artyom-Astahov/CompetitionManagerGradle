package by.artem.spring.database.repository;

import by.artem.spring.database.entity.CompetitionCatalog;
import by.artem.spring.database.entity.Participant;
import by.artem.spring.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Integer>,
        FilterUserRepository, QuerydslPredicateExecutor<Participant> {

    @Query("select p.user from Participant p " +
            "where p.competitionCatalog = :competition")
    List<User> findAllUsersByCompetition(CompetitionCatalog competition);


}

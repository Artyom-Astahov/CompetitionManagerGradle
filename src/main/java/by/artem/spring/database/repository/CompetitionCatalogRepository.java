package by.artem.spring.database.repository;


import by.artem.spring.database.entity.CompetitionCatalog;
import by.artem.spring.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitionCatalogRepository extends JpaRepository<CompetitionCatalog, Integer>,
        FilterCompetitionCatalogRepository, QuerydslPredicateExecutor<CompetitionCatalog> {


}

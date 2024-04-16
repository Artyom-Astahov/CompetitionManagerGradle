package by.artem.spring.database.repository;


import by.artem.spring.database.entity.CompetitionCatalog;
import by.artem.spring.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionCatalogRepository extends JpaRepository<CompetitionCatalog, Integer>,
        FilterCompetitionCatalogRepository, QuerydslPredicateExecutor<CompetitionCatalog> {

}

package by.artem.spring.database.repository;


import by.artem.spring.database.entity.CompetitionCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionCatalogRepository extends JpaRepository<CompetitionCatalog, Integer> {

}

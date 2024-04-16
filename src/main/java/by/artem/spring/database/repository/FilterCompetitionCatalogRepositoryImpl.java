package by.artem.spring.database.repository;

import by.artem.spring.database.entity.CompetitionCatalog;
import by.artem.spring.database.entity.QCompetitionCatalog;
import by.artem.spring.dto.CompetitionCatalogFilter;

import by.artem.spring.dto.QPredicates;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static by.artem.spring.database.entity.QCompetitionCatalog.competitionCatalog;

@RequiredArgsConstructor
public class FilterCompetitionCatalogRepositoryImpl implements FilterCompetitionCatalogRepository {

    private final EntityManager entityManager;

    @Override
    public List<CompetitionCatalog> findAllByFilter(CompetitionCatalogFilter filter) {
        var predicate = QPredicates.builder()
                .add(filter.description(), competitionCatalog.description::containsIgnoreCase)
                .add(filter.dateEvent(), competitionCatalog.dateEvent::before)
                .build();

        return new JPAQuery<CompetitionCatalog>(entityManager)
                .select(competitionCatalog)
                .from(competitionCatalog)
                .where(predicate)
                .fetch();
    }
}

package by.artem.spring.database.repository;

import by.artem.spring.database.entity.CompetitionCatalog;
import by.artem.spring.dto.CompetitionCatalogFilter;

import java.util.List;

public interface FilterCompetitionCatalogRepository {

    List<CompetitionCatalog> findAllByFilter(CompetitionCatalogFilter filter);
}

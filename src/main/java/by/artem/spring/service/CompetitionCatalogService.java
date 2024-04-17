package by.artem.spring.service;



import by.artem.spring.database.repository.CompetitionCatalogRepository;
import by.artem.spring.dto.CompetitionCatalogFilter;
import by.artem.spring.dto.CompetitionCatalogReadDto;
import by.artem.spring.dto.QPredicates;
import by.artem.spring.mapper.CompetitionCatalogMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static by.artem.spring.database.entity.QCompetitionCatalog.*;


@Service
@RequiredArgsConstructor
public class CompetitionCatalogService {

    private final CompetitionCatalogRepository competitionCatalogRepository;
    private final CompetitionCatalogMapper competitionCatalogMapper;

    public Page<CompetitionCatalogReadDto> findAll(CompetitionCatalogFilter filter, Pageable pageable){
        var predicate = QPredicates.builder()
                .add(filter.description(), competitionCatalog.description::containsIgnoreCase)
                .add(filter.dateEvent(), competitionCatalog.dateEvent::before)
                .build();
        return competitionCatalogRepository.findAll(predicate, pageable)
                .map(competitionCatalogMapper::toDto);
    }

    public Optional<CompetitionCatalogReadDto> findById(Integer id){
        return competitionCatalogRepository.findById(id)
                .map(competitionCatalogMapper::toDto);
    }

    //TODO update()
    //TODO delete()
    //TODO create()

}

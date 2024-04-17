package by.artem.spring.service;



import by.artem.spring.database.repository.CompetitionCatalogRepository;
import by.artem.spring.dto.*;
import by.artem.spring.mapper.CompetitionCatalogMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static by.artem.spring.database.entity.QCompetitionCatalog.*;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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

    @Transactional
    public Optional<CompetitionCatalogReadDto> update(Integer id, CompetitionCreateEditDto competitionCreateEditDto) {
        return competitionCatalogRepository.findById(id)
                .map(entity -> competitionCatalogMapper.ReadDtoToCreateDto(competitionCreateEditDto, entity))
                .map(competitionCatalogRepository::saveAndFlush)
                .map(competitionCatalogMapper::toDto);
    }
    @Transactional
    public boolean delete(Integer id) {
        return competitionCatalogRepository.findById(id)
                .map(entity -> {
                    competitionCatalogRepository.delete(entity);
                    competitionCatalogRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public CompetitionCatalogReadDto create(CompetitionCreateEditDto competitionCreateEditDto) {
        return Optional.of(competitionCreateEditDto)
                .map(competitionCatalogMapper::toEntity)
                .map(competitionCatalogRepository::save)
                .map(competitionCatalogMapper::toDto)
                .orElseThrow();
    }

}

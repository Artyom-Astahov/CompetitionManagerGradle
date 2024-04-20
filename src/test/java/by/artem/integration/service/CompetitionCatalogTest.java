package by.artem.integration.service;

import by.artem.annotation.IT;
import by.artem.spring.database.entity.*;
import by.artem.spring.database.repository.CompetitionCatalogRepository;
import by.artem.spring.database.repository.UserRepository;
import by.artem.spring.dto.*;
import by.artem.spring.mapper.CompetitionCatalogMapper;
import by.artem.spring.service.CompetitionCatalogService;
import by.artem.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
public class CompetitionCatalogTest {

    private final CompetitionCatalogRepository competitionCatalogRepository;
    private final CompetitionCatalogService competitionCatalogService;
    private final CompetitionCatalogMapper competitionCatalogMapper;


    @Test
    public void findById() {
        var result = competitionCatalogService.findById(1);

        assertThat(result).isPresent();
        assertThat(result.get().getDescription()).isEqualTo("SBD Sheffield");


    }

    @Test
    public void createCompetition() {
        LocalDateTime date = LocalDateTime.of(2025, 01, 01, 9, 01, 00);

        CompetitionCreateEditDto competitionDto = new CompetitionCreateEditDto(date, "Test description");

        CompetitionCatalogReadDto readDto = competitionCatalogService.create(competitionDto);

        assertThat(readDto).isNotNull();
        assertThat(readDto.getDescription()).isEqualTo("Test description");
        assertThat(readDto.getDateEvent()).isEqualTo(date);
    }


    @Test
    public void testUpdateCompetition() {

        var maybeCompetition = competitionCatalogRepository.findById(1).get();

        assertNotNull(maybeCompetition);
        CompetitionCatalogReadDto readDto = competitionCatalogMapper.toDto(maybeCompetition);

        readDto.setDescription("Update Test");

        assertThat(readDto.getDateEvent()).isEqualTo("Update Test");

    }

    @Test
    void delete() {
        assertFalse(competitionCatalogService.delete(-124));
        assertTrue(competitionCatalogService.delete(1));
    }
}

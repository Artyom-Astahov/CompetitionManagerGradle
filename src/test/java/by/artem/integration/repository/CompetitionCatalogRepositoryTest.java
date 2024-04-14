package by.artem.integration.repository;

import by.artem.annotation.IT;


import by.artem.spring.database.entity.CompetitionCatalog;
import by.artem.spring.database.entity.User;
import by.artem.spring.database.repository.CompetitionCatalogRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
public class CompetitionCatalogRepositoryTest {

    private final CompetitionCatalogRepository competitionCatalogRepository;
    private final EntityManager entityManager;

    @Test
    void findById(){
        var competition = competitionCatalogRepository.findById(1);
        assertThat(competition.get().getDescription()).contains("SBD Sheffield");
    }

    @Test
    void delete(){
        competitionCatalogRepository.deleteById(1);
        var maybeUser = entityManager.find(User.class, 1);
        assertNull(maybeUser);
    }

    @Test
    void save(){
        CompetitionCatalog competitionCatalog = CompetitionCatalog.builder()
                .dateEvent(LocalDateTime.now())
                .description("Test description")
                .build();
        competitionCatalogRepository.save(competitionCatalog);
        assertNotNull(competitionCatalog.getId());
        var maybeCompetition = entityManager.find(CompetitionCatalog.class, competitionCatalog.getId());
        assertThat(maybeCompetition.getDescription()).contains("Test description");
    }
}

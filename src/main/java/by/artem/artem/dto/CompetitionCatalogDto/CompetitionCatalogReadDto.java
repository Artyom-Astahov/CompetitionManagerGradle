package by.artem.artem.dto.CompetitionCatalogDto;

import java.time.LocalDateTime;

public record CompetitionCatalogReadDto(
        Integer id,

        LocalDateTime dateEvent,
        String description) {
}

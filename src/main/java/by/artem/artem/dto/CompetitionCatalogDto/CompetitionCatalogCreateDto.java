package by.artem.artem.dto.CompetitionCatalogDto;

import java.time.LocalDateTime;

public record CompetitionCatalogCreateDto(LocalDateTime dateEvent,
                                          String description) {
}

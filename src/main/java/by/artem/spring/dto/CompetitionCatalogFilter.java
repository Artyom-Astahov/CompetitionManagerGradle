package by.artem.spring.dto;

import java.time.LocalDateTime;

public record CompetitionCatalogFilter(LocalDateTime dateEvent,
                                       String description) {
}

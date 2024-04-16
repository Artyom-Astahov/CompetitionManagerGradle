package by.artem.spring.mapper;

import by.artem.spring.database.entity.CompetitionCatalog;
import by.artem.spring.dto.CompetitionCatalogReadDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Mapper(componentModel = "spring")
public interface CompetitionCatalogMapper {
    CompetitionCatalogReadDto toDto(CompetitionCatalog competitionCatalog);
}

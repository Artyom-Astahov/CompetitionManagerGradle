package by.artem.spring.mapper;

import by.artem.spring.database.entity.CompetitionCatalog;
import by.artem.spring.database.entity.User;
import by.artem.spring.dto.CompetitionCatalogReadDto;
import by.artem.spring.dto.CompetitionCreateEditDto;
import by.artem.spring.dto.UserCreateEditDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Mapper(componentModel = "spring")
public interface CompetitionCatalogMapper {
    CompetitionCatalogReadDto toDto(CompetitionCatalog competitionCatalog);
    CompetitionCatalog toEntity(CompetitionCreateEditDto competitionCreateEditDto);
    CompetitionCatalog toEntity(CompetitionCatalogReadDto competitionCatalogReadDto);

    default CompetitionCatalog ReadDtoToCreateDto(CompetitionCreateEditDto fromObject, CompetitionCatalog toObject){
        toObject.setDateEvent(fromObject.getDateEvent());
        toObject.setDescription(fromObject.getDescription());
        return toObject;
    }
}

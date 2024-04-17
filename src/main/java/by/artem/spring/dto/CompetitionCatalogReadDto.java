package by.artem.spring.dto;



import lombok.Value;


import java.time.LocalDateTime;
import java.util.List;

@Value
public class CompetitionCatalogReadDto {

    Integer id;
    LocalDateTime dateEvent;
    String description;
    List<UserReadDto> users;
}

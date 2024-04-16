package by.artem.spring.dto;



import lombok.Value;


import java.time.LocalDateTime;

@Value
public class CompetitionCatalogReadDto {

    Integer id;
    LocalDateTime dateEvent;
    String description;
}

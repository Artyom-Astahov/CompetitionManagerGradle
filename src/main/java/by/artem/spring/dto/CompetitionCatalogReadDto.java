package by.artem.spring.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldNameConstants;


import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class CompetitionCatalogReadDto {

    Integer id;
    LocalDateTime dateEvent;
    String description;
    List<UserReadDto> users;
}

package by.artem.spring.dto;

import by.artem.spring.database.entity.SportCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoReadDto {
    Integer id;
    String name;
    Integer weight;
    SportCategoryEnum category;
    LocalDate dateBirth;
}

package by.artem.spring.dto;

import by.artem.spring.database.entity.SportCategoryEnum;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UserInfoReadDto {
    Integer id;
    String name;
    Integer weight;
    SportCategoryEnum category;
    LocalDate dateBirth;
}

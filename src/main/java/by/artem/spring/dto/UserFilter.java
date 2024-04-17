package by.artem.spring.dto;

import by.artem.spring.database.entity.RolesEnum;
import by.artem.spring.database.entity.SportCategoryEnum;

import java.time.LocalDate;

public record UserFilter(String name,
                         Integer weight,
                         SportCategoryEnum category,
                         LocalDate dateBirth,
                         RolesEnum role) {
}

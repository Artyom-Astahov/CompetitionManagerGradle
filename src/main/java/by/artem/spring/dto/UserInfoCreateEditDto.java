package by.artem.spring.dto;

import by.artem.spring.database.entity.SportCategoryEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
@Validated
@FieldNameConstants
@Value
public class UserInfoCreateEditDto {
    @NotBlank
    String name;
//    @Size(min = 50, max = 200)
    Integer weight;
    SportCategoryEnum category;
    @Past
    LocalDate dateBirth;
}

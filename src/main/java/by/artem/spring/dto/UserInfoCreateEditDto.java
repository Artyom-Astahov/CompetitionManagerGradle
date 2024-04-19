package by.artem.spring.dto;

import by.artem.spring.database.entity.SportCategoryEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
@Validated
@FieldNameConstants
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoCreateEditDto {
    @NotBlank
    String name;
    @Size(min = 50, max = 200)
    Integer weight;
    @NotBlank
    SportCategoryEnum category;
    @Past
    LocalDate dateBirth;
}

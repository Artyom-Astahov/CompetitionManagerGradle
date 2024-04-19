package by.artem.spring.dto;

import by.artem.spring.database.entity.RolesEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;


@Validated
@FieldNameConstants
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateEditDto {
    @NotBlank
    String login;
    @Size(min = 3, max = 30)
    String password;
    @NotNull
    RolesEnum role;
    UserInfoCreateEditDto userInfo = new UserInfoCreateEditDto();

}

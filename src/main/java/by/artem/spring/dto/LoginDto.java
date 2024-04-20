package by.artem.spring.dto;

import lombok.Value;
import lombok.experimental.FieldNameConstants;


@Value
@FieldNameConstants
public class LoginDto {
    String login;
    String password;
}

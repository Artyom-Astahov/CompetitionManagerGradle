package by.artem.spring.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@Value
@FieldNameConstants
public class CompetitionCreateEditDto {
    @FutureOrPresent
    LocalDateTime dateEvent;
    @NotBlank
    String description;
}

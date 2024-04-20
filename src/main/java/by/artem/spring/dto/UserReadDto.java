package by.artem.spring.dto;

import by.artem.spring.database.entity.RolesEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class UserReadDto {
    Integer id;
    String login;
    String password;
    RolesEnum role;
    UserInfoReadDto userInfo;
    //TODO Почему-то не выдает ошибку, при маппинге
//    List<CompetitionCatalogReadDto> competitionCatalogs;

}

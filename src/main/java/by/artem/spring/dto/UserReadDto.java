package by.artem.spring.dto;

import by.artem.spring.database.entity.RolesEnum;
import lombok.Value;

import java.util.List;

@Value
public class UserReadDto {
    Integer id;
    String login;
    String password;
    RolesEnum role;
    UserInfoReadDto userInfo;
//    List<CompetitionCatalogReadDto> competitionCatalogs;

}

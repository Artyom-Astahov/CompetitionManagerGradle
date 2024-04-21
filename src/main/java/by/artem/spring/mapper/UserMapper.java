package by.artem.spring.mapper;

import by.artem.spring.database.entity.CompetitionCatalog;
import by.artem.spring.database.entity.User;
import by.artem.spring.database.entity.UserInfo;
import by.artem.spring.dto.CompetitionCreateEditDto;
import by.artem.spring.dto.UserCreateEditDto;
import by.artem.spring.dto.UserInfoCreateEditDto;
import by.artem.spring.dto.UserReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserReadDto toDto(User user);
    User toEntity(UserCreateEditDto userCreateEditDto);
    UserInfo toEntityUserInfo(UserInfoCreateEditDto userInfoCreateEditDto);

    default User readDtoToCreateDto(UserReadDto fromObject, User toObject){
        toObject.setRole(fromObject.getRole());
        toObject.setLogin(fromObject.getLogin());
        toObject.getUserInfo().setCategory(fromObject.getUserInfo().getCategory());
        toObject.getUserInfo().setName(fromObject.getUserInfo().getName());
        toObject.getUserInfo().setWeight(fromObject.getUserInfo().getWeight());
        toObject.getUserInfo().setDateBirth(fromObject.getUserInfo().getDateBirth());
        return toObject;
    }
}

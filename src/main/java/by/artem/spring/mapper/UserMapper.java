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
    @Mapping(target = "userInfo", source = "userInfo")
    User toEntity(UserCreateEditDto userCreateEditDto);
    UserInfo toEntityUserInfo(UserInfoCreateEditDto userInfoCreateEditDto);

    default User ReadDtoToCreateDto(UserCreateEditDto fromObject, UserInfoCreateEditDto userInfoDto, User toObject){
        toObject.setRole(fromObject.getRole());
        toObject.setLogin(fromObject.getLogin());
        toObject.getUserInfo().setCategory(userInfoDto.getCategory());
        toObject.getUserInfo().setName(userInfoDto.getName());
        toObject.getUserInfo().setWeight(userInfoDto.getWeight());
        toObject.getUserInfo().setDateBirth(userInfoDto.getDateBirth());
        return toObject;
    }
}

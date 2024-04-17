package by.artem.spring.mapper;

import by.artem.spring.database.entity.User;
import by.artem.spring.dto.UserReadDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserReadDto toDto(User user);
}

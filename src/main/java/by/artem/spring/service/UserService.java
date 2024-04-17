package by.artem.spring.service;


import by.artem.spring.database.entity.User;
import by.artem.spring.database.repository.UserRepository;
import by.artem.spring.dto.*;
import by.artem.spring.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static by.artem.spring.database.entity.QUser.user;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Page<UserReadDto> findAll(UserFilter filter, Pageable pageable) {
        var predicate = QPredicates.builder()
                .add(filter.name(), user.userInfo.name::containsIgnoreCase)
                .add(filter.weight(), user.userInfo.weight::lt)
                .add(filter.category(), user.userInfo.category::eq)
                .add(filter.dateBirth(), user.userInfo.dateBirth::before)
                .add(filter.role(), user.role::eq)
                .build();

        return userRepository.findAll(predicate, pageable)
                .map(userMapper::toDto);
    }

    public Optional<UserReadDto> findById(Integer id){
        return userRepository.findById(id)
                .map(userMapper::toDto);
    }

    //TODO update()
    //TODO create()
    //TODO delete()

}

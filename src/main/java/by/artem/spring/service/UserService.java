package by.artem.spring.service;


import by.artem.spring.database.entity.User;
import by.artem.spring.database.entity.UserInfo;
import by.artem.spring.database.repository.UserRepository;
import by.artem.spring.dto.*;
import by.artem.spring.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static by.artem.spring.database.entity.QUser.user;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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

    public Optional<UserReadDto> findById(Integer id) {
        Optional<UserReadDto> obj = userRepository.findById(id)
                .map(userMapper::toDto);
        return obj;
    }

    @Transactional
    public Optional<UserReadDto> update(Integer id, UserReadDto userDto) {

        return userRepository.findById(id)
                .map(entity -> userMapper.readDtoToCreateDto(userDto,  entity))
                .map(userRepository::saveAndFlush)
                .map(userMapper::toDto);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(userMapper::toEntity)
                .map(user -> {
                        UserInfo userInfo = userMapper.toEntityUserInfo(userDto.getUserInfo());
                        userInfo.setUser(user);
                        user.setUserInfo(userInfo);
                        return user;
                })
                .map(userRepository::save)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Unable to create User"));
    }


    @Transactional
    public boolean delete(Integer id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }

}

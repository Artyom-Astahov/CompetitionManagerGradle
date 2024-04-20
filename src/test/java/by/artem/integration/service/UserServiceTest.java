package by.artem.integration.service;

import by.artem.annotation.IT;
import by.artem.spring.database.entity.RolesEnum;
import by.artem.spring.database.entity.SportCategoryEnum;
import by.artem.spring.database.entity.User;
import by.artem.spring.database.entity.UserInfo;
import by.artem.spring.database.repository.UserRepository;
import by.artem.spring.dto.*;
import by.artem.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
public class UserServiceTest {

    private final UserRepository userRepository;
    private final UserService userService;


//    @Test
//    void findAll() {
//        List<UserReadDto> result = userService.findAll();
//        assertThat(result).hasSize(6);
//    }


    @Test
    public void findById() {
        User user = new User();
        user.setLogin("Test Login");
        user.setPassword("Test Password");
        user.setRole(RolesEnum.ADMIN);
        user = userRepository.save(user);

        Optional<UserReadDto> userDto = userService.findById(user.getId());

        assertThat(userDto).isPresent();
        assertThat(userDto.get().getLogin()).isEqualTo("Test Login");
        assertThat(userDto.get().getPassword()).isEqualTo("Test Password");
        assertThat(userDto.get().getRole()).isEqualTo(RolesEnum.ADMIN);
    }

    @Test
    public void testCreateUser() {
        UserCreateEditDto userDto = new UserCreateEditDto();
        userDto.setLogin("New User");
        LocalDate date = LocalDate.now();
        userDto.setUserInfo(new UserInfoCreateEditDto("New Info", 85, SportCategoryEnum.KMS,
                date));

        UserReadDto createdUser = userService.create(userDto);


        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getLogin()).isEqualTo("New User");
        assertThat(createdUser.getUserInfo().getName()).isEqualTo("New Info");
        assertThat(createdUser.getUserInfo().getWeight()).isEqualTo(85);
        assertThat(createdUser.getUserInfo().getCategory()).isEqualTo(SportCategoryEnum.KMS);
        assertThat(createdUser.getUserInfo().getDateBirth()).isEqualTo(date);
    }


    @Test
    public void testUpdateUser() {

        User user = new User();
        user.setLogin("Old User");
        user.setPassword("111");
        LocalDate date = LocalDate.now();
        user.setRole(RolesEnum.ADMIN);
        UserInfo userInfo = UserInfo.builder()
                .name("Old Info")
                .weight(85)
                .category(SportCategoryEnum.KMS)
                .dateBirth(date)
                .build();
        user.setUserInfo(userInfo);
        userInfo.setUser(user);

        user = userRepository.save(user);


        UserInfoReadDto userInfoReadDto = new UserInfoReadDto(user.getUserInfo().getId(), userInfo.getName(),
                userInfo.getWeight(), userInfo.getCategory(), userInfo.getDateBirth());
        UserReadDto userDto = new UserReadDto(user.getId(), "Old User", "111", RolesEnum.ADMIN, userInfoReadDto);

        userDto.setLogin("Updated User");
        userDto.getUserInfo().setName("New Name");

        Optional<UserReadDto> updatedUser = userService.update(user.getId(), userDto);

        assertThat(updatedUser).isPresent();
        assertThat(updatedUser.get().getLogin()).isEqualTo("Updated User");
        assertThat(updatedUser.get().getUserInfo().getName()).isEqualTo("New Name");
    }

    @Test
    void delete(){
        assertFalse(userService.delete(-124));
        assertTrue(userService.delete(1));
    }
}

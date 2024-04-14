package by.artem.integration.repository;

import by.artem.annotation.IT;
import by.artem.spring.database.entity.*;
import by.artem.spring.database.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
public class UserRepositoryTest {

    private final UserRepository userRepository;
    private final EntityManager entityManager;

    @Test
    void findById(){
        var user = userRepository.findById(1);
        assertThat(user.get().getUserInfo().getName()).contains("Ed Cohen");
    }

    @Test
    void delete(){
        userRepository.deleteById(1);
        var maybeUser = entityManager.find(User.class, 1);
        assertNull(maybeUser);
    }

    @Test
    void save(){
        User user = User.builder()
                .userInfo(UserInfo.builder()
                        .category(SportCategoryEnum.MS)
                        .dateBirth(LocalDate.now())
                        .name("Test name")
                        .weight(55)
                        .build())
                .login("testLogin")
                .password("testPassword")
                .role(RolesEnum.ADMIN)
                .build();
        userRepository.save(user);
        assertNotNull(user.getId());
        var maybeUser = entityManager.find(User.class, user.getId());
        assertThat(maybeUser.getUserInfo().getName()).contains("Test name");
        assertThat(maybeUser.getRole()).isEqualTo(RolesEnum.ADMIN);
    }



}

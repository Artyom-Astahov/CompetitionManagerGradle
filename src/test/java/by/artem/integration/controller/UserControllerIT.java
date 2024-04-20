package by.artem.integration.controller;

import by.artem.annotation.IT;
import by.artem.spring.database.entity.RolesEnum;
import by.artem.spring.database.entity.SportCategoryEnum;
import lombok.RequiredArgsConstructor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static by.artem.spring.dto.UserCreateEditDto.Fields.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IT
@RequiredArgsConstructor
@AutoConfigureMockMvc
public class UserControllerIT {

    private final MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attributeExists("filter"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("roles"));
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(post("/users")
                        .param(login, "edCohen")
                        .param(password, "123")
                        .param(role, "ATHLETE")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/users/{\\d+}")
                );
    }

    @Test
    void update() throws Exception {

        Integer userId = 1;
        String newLogin = "newLogin";
        String newPassword = "newPassword";
        RolesEnum newRole = RolesEnum.ADMIN;
        String newName = "New Name";
        int newWeight = 75;
        SportCategoryEnum newCategory = SportCategoryEnum.KMS;
        LocalDate newDateOfBirth = LocalDate.of(1990, 1, 1);

        mockMvc.perform(post("/users/1/update")
                        .param("login", newLogin)
                        .param("password", newPassword)
                        .param("role", newRole.toString())
                        .param("userInfo.name", newName)
                        .param("userInfo.weight", String.valueOf(newWeight))
                        .param("userInfo.category", newCategory.toString())
                        .param("userInfo.dateBirth", newDateOfBirth.toString())
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/users/{\\d+}")
                );
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/users")
                        .param(login, "test@gmail.com")
                        .param(password, "Test")
                        .param(role, "ADMIN")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/users/{\\d+}")
                );
    }


    @Test
    void delete() throws Exception {
        mockMvc.perform(post("/users/1/delete")
                        .param("id", "1"))
                .andExpect(redirectedUrl("/users"));
    }

}

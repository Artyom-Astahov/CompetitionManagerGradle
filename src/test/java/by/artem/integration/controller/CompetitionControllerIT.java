package by.artem.integration.controller;

import by.artem.annotation.IT;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static by.artem.spring.dto.CompetitionCatalogReadDto.Fields.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@IT
@RequiredArgsConstructor
@AutoConfigureMockMvc
public class CompetitionControllerIT {

    private final MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/competitions"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("competition/competitions"))
                .andExpect(model().attributeExists("competitions"))
                .andExpect(model().attributeExists("filter"));

    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/competitions/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("competition/competition"));
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(post("/competitions/{id}/update", 1)
                        .param("description", "newDescription"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/competitions/{\\d+}"));
    }


    @Test
    void delete() throws Exception {
        mockMvc.perform(post("/competitions/1/delete")
                        .param("id", "1"))
                .andExpect(redirectedUrl("/competitions"));
    }
}

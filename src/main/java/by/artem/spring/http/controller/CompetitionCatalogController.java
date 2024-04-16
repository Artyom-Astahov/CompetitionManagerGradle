package by.artem.spring.http.controller;

import by.artem.spring.dto.CompetitionCatalogFilter;
import by.artem.spring.dto.CompetitionCatalogReadDto;
import by.artem.spring.dto.PageResponse;
import by.artem.spring.service.CompetitionCatalogService;
import by.artem.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/competitions")
@RequiredArgsConstructor
@Slf4j
public class CompetitionCatalogController {
    private final CompetitionCatalogService competitionCatalogService;
    private final UserService userService;

    @GetMapping
    public String findAll(Model model, CompetitionCatalogFilter filter, Pageable pageable){
        Page<CompetitionCatalogReadDto> page = competitionCatalogService.findAll(filter, pageable);
        model.addAttribute("competitions", PageResponse.of(page));
        model.addAttribute("filter", filter);
        return "competition/competitions";
    }

}

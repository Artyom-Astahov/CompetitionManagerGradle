package by.artem.spring.http.controller;

import by.artem.spring.dto.*;
import by.artem.spring.service.CompetitionCatalogService;
import by.artem.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/competitions")
@RequiredArgsConstructor
@Slf4j
public class CompetitionCatalogController {
    private final CompetitionCatalogService competitionCatalogService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COACH', 'ATHLETE')")
    @GetMapping
    public String findAll(Model model, CompetitionCatalogFilter filter, Pageable pageable){
        Page<CompetitionCatalogReadDto> page = competitionCatalogService.findAll(filter, pageable);
        model.addAttribute("competitions", PageResponse.of(page));
        model.addAttribute("filter", filter);
        return "competition/competitions";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'COACH', 'ATHLETE')")
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model){
        return competitionCatalogService.findById(id)
                .map(competition -> {
                    model.addAttribute("competition", competition);
                    model.addAttribute("users", competition.getUsers());
                    return "competition/competition";
                        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id, @ModelAttribute @Validated CompetitionCreateEditDto competitionCreateEditDto) {
        return competitionCatalogService.update(id, competitionCreateEditDto)
                .map(it -> "redirect:/competitions/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        competitionCatalogService.delete(id);
        return "redirect:/competitions";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/create")
    public String createCompetition(@ModelAttribute @Validated CompetitionCreateEditDto competitionCreateDto,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("competition", competitionCreateDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/competitions/create";
        }
        CompetitionCatalogReadDto dto =  competitionCatalogService.create(competitionCreateDto);
        return "redirect:/competitions/" + dto.getId();
    }
}

package by.artem.spring.http.controller;

import by.artem.spring.database.entity.CompetitionCatalog;
import by.artem.spring.database.entity.RolesEnum;
import by.artem.spring.database.entity.User;
import by.artem.spring.dto.*;
import by.artem.spring.mapper.UserMapper;
import by.artem.spring.service.CompetitionCatalogService;
import by.artem.spring.service.ParticipantService;
import by.artem.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/competitions")
@RequiredArgsConstructor
@Slf4j
public class CompetitionCatalogController {

    private final CompetitionCatalogService competitionCatalogService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final ParticipantService participantService;


    @PreAuthorize("hasAnyAuthority('ADMIN', 'COACH', 'ATHLETE')")
    @GetMapping
    public String findAll(Model model, CompetitionCatalogFilter filter, Pageable pageable) {
        Page<CompetitionCatalogReadDto> page = competitionCatalogService.findAll(filter, pageable);
        model.addAttribute("competitions", PageResponse.of(page));
        model.addAttribute("filter", filter);
        return "competition/competitions";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COACH', 'ATHLETE')")
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model) {
        return competitionCatalogService.findById(id)
                .map(competition -> {
                    model.addAttribute("competition", competition);
                    model.addAttribute("users", participantService.findAllUsersByCompetition(competition));
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
    @GetMapping("/create")
    public String getPageCreateCompetition(Model model,
                                           @ModelAttribute("competition")
                                           CompetitionCreateEditDto competitionCreateDto) {
        model.addAttribute("competition", competitionCreateDto);
        return "competition/create";

    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/create")
    public String createCompetition(@ModelAttribute @Validated CompetitionCreateEditDto competitionCreateDto,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("competition", competitionCreateDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/competitions/create";
        }
        CompetitionCatalogReadDto dto = competitionCatalogService.create(competitionCreateDto);
        return "redirect:/competitions/" + dto.getId();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/{id}/participant")
    public String getPageAddParticipant(@PathVariable("id") Integer id,
                                        @ModelAttribute CompetitionCatalogReadDto competitionReadDto,
                                        Model model) {

        List<User> listUsers = userService.findAllByRoleAthlete();
        List<User> listUsersReadDtoToEntity = participantService.findAllUsersByCompetition(competitionReadDto).stream()
                .map(userReadDto -> userMapper.toEntity(userReadDto))
                .collect(Collectors.toList());

        listUsers = listUsers.stream()
                .filter(user -> !listUsersReadDtoToEntity.stream()
                        .anyMatch(userInList -> user.getId().equals(userInList.getId())))
                .collect(Collectors.toList());

        model.addAttribute("competition", competitionReadDto);
        model.addAttribute("users", listUsers);
        return "competition/participant";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/{id}/participant")
    public String addParticipant(@ModelAttribute("userIds") ArrayList<User> userIds,
                                 @ModelAttribute CompetitionCatalogReadDto competitionReadDto) {
        userIds.forEach(userEntity -> participantService.saveUserAndCompetition(competitionReadDto, userEntity));
        return "redirect:/competitions/" + competitionReadDto.getId();
    }
}

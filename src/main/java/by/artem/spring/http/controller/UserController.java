package by.artem.spring.http.controller;

import by.artem.spring.database.entity.RolesEnum;
import by.artem.spring.database.entity.SportCategoryEnum;
import by.artem.spring.dto.PageResponse;
import by.artem.spring.dto.UserFilter;
import by.artem.spring.dto.UserReadDto;
import by.artem.spring.service.CompetitionCatalogService;
import by.artem.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;


@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final CompetitionCatalogService competitionCatalogService;

    @GetMapping
    public String findAll(Model model, UserFilter filter, Pageable pageable){
        Page<UserReadDto> page = userService.findAll(filter, pageable);
        model.addAttribute("users", PageResponse.of(page));
        model.addAttribute("filter", filter);
        model.addAttribute("categories", SportCategoryEnum.values());
        model.addAttribute("roles", RolesEnum.values());
        return "user/users";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model){
        return userService.findById(id)
                .map(user -> {
                    model.addAttribute("user", user);
                    model.addAttribute("categories", SportCategoryEnum.values());
                    model.addAttribute("roles", RolesEnum.values());
                    return "user/user";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


}

package by.artem.spring.http.controller;

import by.artem.spring.database.entity.RolesEnum;
import by.artem.spring.database.entity.SportCategoryEnum;
import by.artem.spring.dto.*;
import by.artem.spring.service.CompetitionCatalogService;
import by.artem.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static by.artem.spring.database.entity.RolesEnum.*;


@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final CompetitionCatalogService competitionCatalogService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COACH', 'ATHLETE')")
    @GetMapping
    public String findAll(Model model, UserFilter filter, Pageable pageable) {
        Page<UserReadDto> page = userService.findAll(filter, pageable);
        model.addAttribute("users", PageResponse.of(page));
        model.addAttribute("filter", filter);
        model.addAttribute("categories", SportCategoryEnum.values());
        model.addAttribute("roles", values());
        return "user/users";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COACH', 'ATHLETE')")
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model) {
        return userService.findById(id)
                .map(user -> {
                    model.addAttribute("user", user);
                    model.addAttribute("categories", SportCategoryEnum.values());
                    model.addAttribute("roles", values());
                    return "user/user";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COACH', 'ATHLETE')")
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id, @ModelAttribute @Validated UserReadDto userReadDto,
                         @AuthenticationPrincipal UserDetails userDetails) {


        return userService.update(id, userReadDto)
                .map(it -> {
                    if (userDetails.getUsername().contains(it.getLogin()) ||
                            userDetails.getAuthorities().contains(ADMIN)) {
                        return "redirect:/users/{id}";
                    } else {
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                    }

                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/register")
    public String create(@ModelAttribute @Validated UserCreateEditDto user,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/users/registration";
        }
        userService.create(user);
        return "redirect:/login";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'COACH', 'ATHLETE')")
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id,
                         @AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails.getUsername().contains(userService.findById(id).get().getLogin()) ||
                userDetails.getAuthorities().contains(ADMIN)) {
            userService.delete(id);
            return "redirect:/users";
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }


    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute("user") UserCreateEditDto user) {
        model.addAttribute("user", user);
        model.addAttribute("roles", values());
        model.addAttribute("categories", SportCategoryEnum.values());
        return "user/registration";
    }

}

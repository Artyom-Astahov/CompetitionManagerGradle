package by.artem.spring.http.rest;

import by.artem.spring.dto.*;
import by.artem.spring.service.CompetitionCatalogService;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/competitions")
public class CompetitionRestController {

    private final CompetitionCatalogService competitionService;
    @GetMapping
    public PageResponse<CompetitionCatalogReadDto> findAll(CompetitionCatalogFilter filter, Pageable pageable) {
        Page<CompetitionCatalogReadDto> page = competitionService.findAll(filter, pageable);
        return PageResponse.of(page);
    }

    @GetMapping("/{id}")
    public CompetitionCatalogReadDto findById(@PathVariable("id") Integer id){
        return competitionService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CompetitionCatalogReadDto create(@Validated @RequestBody CompetitionCreateEditDto competitionDto){
        return competitionService.create(competitionDto);
    }

    @PutMapping("/{id}")
    public CompetitionCatalogReadDto update(@PathVariable("id") Integer id ,@RequestBody CompetitionCreateEditDto competitionCreateEditDto){
        return competitionService.update(id, competitionCreateEditDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        return competitionService.delete(id)
                ? noContent().build()
                : notFound().build();
    }

}

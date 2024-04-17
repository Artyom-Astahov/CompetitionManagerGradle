package by.artem.spring.service;


import by.artem.spring.database.repository.UserRepository;
import by.artem.spring.dto.CompetitionCatalogFilter;
import by.artem.spring.dto.CompetitionCatalogReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;



}

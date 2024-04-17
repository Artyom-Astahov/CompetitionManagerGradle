package by.artem.spring.service;


import by.artem.spring.database.entity.User;
import by.artem.spring.database.repository.UserRepository;
import by.artem.spring.dto.CompetitionCatalogFilter;
import by.artem.spring.dto.CompetitionCatalogReadDto;
import by.artem.spring.dto.UserFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll(UserFilter filter, Pageable pageable){
        return null;
    }
    //TODO findAll()
    //TODO update()
    //TODO create()
    //TODO delete()

}

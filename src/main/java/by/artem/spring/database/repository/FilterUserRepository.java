package by.artem.spring.database.repository;


import by.artem.spring.database.entity.User;
import by.artem.spring.dto.CompetitionCatalogFilter;
import by.artem.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByFilter(UserFilter filter);
}

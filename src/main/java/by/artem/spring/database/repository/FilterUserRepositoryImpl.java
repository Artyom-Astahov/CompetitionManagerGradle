package by.artem.spring.database.repository;

import by.artem.spring.database.entity.CompetitionCatalog;
import by.artem.spring.database.entity.QUser;
import by.artem.spring.database.entity.User;
import by.artem.spring.dto.QPredicates;
import by.artem.spring.dto.UserFilter;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import static by.artem.spring.database.entity.QCompetitionCatalog.competitionCatalog;
import static by.artem.spring.database.entity.QUser.*;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository{

    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        var predicate = QPredicates.builder()
                .add(filter.name(), user.userInfo.name::containsIgnoreCase)
                .add(filter.weight(), user.userInfo.weight::lt)
                .add(filter.category(), user.userInfo.category::eq)
                .add(filter.dateBirth(), user.userInfo.dateBirth::before )
                .build();
        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .join(user.userInfo)
                .where(predicate)
                .fetch();
    }
}

package by.artem.spring.service;



import by.artem.spring.database.repository.CompetitionCatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CompetitionCatalogService {
    private final CompetitionCatalogRepository competitionCatalogRepository;
}

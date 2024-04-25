package by.artem.spring.service;

import by.artem.spring.database.entity.CompetitionCatalog;
import by.artem.spring.database.entity.Participant;
import by.artem.spring.database.entity.User;
import by.artem.spring.database.repository.ParticipantRepository;
import by.artem.spring.dto.CompetitionCatalogReadDto;
import by.artem.spring.dto.UserReadDto;
import by.artem.spring.mapper.CompetitionCatalogMapper;
import by.artem.spring.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private final UserMapper userMapper;
    private final CompetitionCatalogMapper competitionCatalogMapper;


    @Transactional
    public Participant saveUserAndCompetition(CompetitionCatalogReadDto competitionCatalog, User user){

        Participant participant = Participant.builder()
                .competitionCatalog(competitionCatalogMapper.toEntity(competitionCatalog))
                .user(user)
                .build();
        return participantRepository.save(participant);
    }

    public List<UserReadDto> findAllUsersByCompetition(CompetitionCatalogReadDto competitionCatalogReadDto){

        return participantRepository
                .findAllUsersByCompetition(competitionCatalogMapper.toEntity(competitionCatalogReadDto))
                .stream()
                .map(user -> userMapper.toDto(user))
                .collect(Collectors.toList());
    }

}

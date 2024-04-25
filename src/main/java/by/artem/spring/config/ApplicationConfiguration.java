package by.artem.spring.config;




import by.artem.spring.database.repository.pool.ConnectionPool;
import by.artem.spring.dto.UserReadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ConnectionPool connectionPool(@Value("${db.username}") String username,
                                         @Value("${db.password}") String password,
                                         @Value("${db.pool.size}") Integer poolSize,
                                         @Value("${db.url}") String url) {
        return new ConnectionPool(username, password, poolSize, url);
    }











}

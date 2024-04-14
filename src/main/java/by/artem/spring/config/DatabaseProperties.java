package by.artem.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "db")
//@Profile({"prod", "test"})
public record DatabaseProperties(String username,
                                 String password,
                                 String driver,
                                 String url,
                                 String hosts,
                                 PoolProperties pool) {

    public static record PoolProperties(Integer size,
                                        Integer timeout){

    }
}

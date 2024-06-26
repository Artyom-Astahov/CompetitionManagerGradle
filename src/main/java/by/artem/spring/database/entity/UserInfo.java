package by.artem.spring.database.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Slf4j
@ToString(exclude = "user")
@Table(schema = "public")
public class UserInfo implements BaseEntity<Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    private int weight;
    @Enumerated(EnumType.STRING)
    private SportCategoryEnum category;
    private LocalDate dateBirth;


}

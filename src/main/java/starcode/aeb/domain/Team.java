package starcode.aeb.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "team_id")
    private List<User> students;
}

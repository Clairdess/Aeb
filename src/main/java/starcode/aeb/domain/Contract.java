package starcode.aeb.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Contract {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne
    private User user;
    @OneToOne
    private Intern intern;
    private LocalDateTime date;
    private String university;
    private Long course;
    private String resume;
    private StatusContract statusContract;

}

package starcode.aeb.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class Intern {
    @Id
    private Long id;
    private String name;
    private String description;
    private Department department;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd ")
    private LocalDateTime startsAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd ")
    private LocalDateTime endsAt;

    @OneToMany
    @JoinColumn(name = "intern_id")
    private Set<Event> events;

    @OneToMany
    @JoinColumn(name = "intern_id")
    private Set<User> users;

}

package starcode.aeb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Event {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String description;
    private String location;
    private LocalDateTime date;
}

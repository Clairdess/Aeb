package starcode.aeb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import starcode.aeb.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}

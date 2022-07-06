package starcode.aeb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import starcode.aeb.domain.Team;

public interface TeamRepo extends JpaRepository<Team, Long> {
}

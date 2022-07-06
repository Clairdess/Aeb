package starcode.aeb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import starcode.aeb.domain.Intern;

public interface InternRepository extends JpaRepository<Intern, Long> {
}

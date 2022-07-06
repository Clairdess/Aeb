package starcode.aeb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import starcode.aeb.domain.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}

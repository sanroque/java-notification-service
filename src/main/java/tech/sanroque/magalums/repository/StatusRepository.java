package tech.sanroque.magalums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sanroque.magalums.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
}

package edu.snhu.airbook.repo;

import edu.snhu.airbook.entities.TicketStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketStatusRepo extends JpaRepository<TicketStatusEntity, Integer> {
}

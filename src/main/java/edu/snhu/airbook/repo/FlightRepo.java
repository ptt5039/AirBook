package edu.snhu.airbook.repo;

import edu.snhu.airbook.entities.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepo extends JpaRepository<FlightEntity, Integer> {
}

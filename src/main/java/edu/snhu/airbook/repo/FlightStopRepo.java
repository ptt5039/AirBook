package edu.snhu.airbook.repo;

import edu.snhu.airbook.entities.FlightStopEntity;
import edu.snhu.airbook.entities.FlightStopPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightStopRepo extends JpaRepository<FlightStopEntity, FlightStopPK> {
}

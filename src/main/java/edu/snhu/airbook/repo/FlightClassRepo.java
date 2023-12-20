package edu.snhu.airbook.repo;

import edu.snhu.airbook.entities.TravelClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightClassRepo extends JpaRepository<TravelClassEntity, Integer> {
}

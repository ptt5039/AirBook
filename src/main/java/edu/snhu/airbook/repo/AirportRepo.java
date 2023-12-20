package edu.snhu.airbook.repo;

import edu.snhu.airbook.entities.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepo extends JpaRepository<AirportEntity, Integer> {
    List<AirportEntity> findByIataContainingIgnoreCase(String value);

    List<AirportEntity> findByNameContainingIgnoreCase(String value);
}

package edu.snhu.airbook.services;

import edu.snhu.airbook.dto.TravelClassDto;
import edu.snhu.airbook.entities.TravelClassEntity;
import edu.snhu.airbook.repo.TravelClassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Travel Class Service
 *
 * @author phongtran
 */
@Service
public class TravelClassService {
    @Autowired
    TravelClassRepo travelClassRepo;

    /**
     * get all travel classes.
     *
     * @return List of Travel classes
     */
    public List<TravelClassDto> getAllTravelClass() {
        return travelClassRepo
                .findAll()
                .stream()
                .map(this::mapToTravelClassDto).collect(Collectors.toList());
    }

    /**
     * Map Travel Class Entity to Travel Class DTO.
     *
     * @param entity Travel Class Entity
     * @return TravelClass DTO
     */
    private TravelClassDto mapToTravelClassDto(TravelClassEntity entity) {
        return TravelClassDto
                .builder()
                .classId(entity.getClassId())
                .className(entity.getClassName())
                .build();
    }
}

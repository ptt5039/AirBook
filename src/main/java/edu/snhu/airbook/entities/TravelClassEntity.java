package edu.snhu.airbook.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Flight Entity
 * 
 * @author phongtran
 */
@Entity
@Table(name="TRAVEL_CLASS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelClassEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TRAVEL_CLASS_ID")
	private int classId;

	@Column(name="TRAVEL_CLASS_NAME")
	private String className;


}

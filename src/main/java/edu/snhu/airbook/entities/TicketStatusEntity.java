package edu.snhu.airbook.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Ticket status entity
 * 
 * @author phongtran
 */
@Entity
@Table(name="TICKET_STATUS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketStatusEntity {
	@Id
	@Column(name="STATUS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int statusId;

	@Column(name="STATUS_NAME")
	private String statusName;
}

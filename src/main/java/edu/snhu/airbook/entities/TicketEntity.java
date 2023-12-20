package edu.snhu.airbook.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Ticket Entity
 *
 * @author phongtran
 */
@Entity
@Table(name = "TICKET")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TICKET_NUMBER")
    private int ticketNumber;

    @Column(name = "FLIGHT_ID")
    private int flightId;

    @Column(name = "CLASS_ID")
    private int classId;

    @Column(name = "PASSENGER_FIRST_NAME")
    private String passengerFirstName;

    @Column(name = "PASSENGER_LAST_NAME")
    private String passengerLastName;

    @Column(name = "PASSENGER_EMAIL")
    private String passengerEmail;

    @Column(name = "PURCHASE_DATETIME")
    private LocalDateTime purchaseDateTime;

    @Column(name = "TOTAL_COST")
    private double totalCost;

    @Column(name = "STATUS_ID")
    private int statusId;
}

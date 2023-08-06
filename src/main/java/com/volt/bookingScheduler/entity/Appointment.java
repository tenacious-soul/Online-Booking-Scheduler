package com.volt.bookingScheduler.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    /* Added in case we want to have dynamic operators in the system*/
//    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
//    @JoinColumn(name = "operator", nullable = false)
//    private Operator operator;

    private Long operatorId;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    private Integer status;
}

package com.volt.bookingScheduler.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Operator {

    @Id
    private Long operator;

    @OneToMany(mappedBy = "operator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointmentList;
}

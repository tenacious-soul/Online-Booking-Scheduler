package com.volt.bookingScheduler.model.requestDTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RescheduleAppointment implements Serializable {

    @JsonProperty("appointment_id")
    private Long appointmentId;

    @JsonProperty("start_time")
    private String startTime;

    @JsonProperty("end_time")
    private String endTime;
}

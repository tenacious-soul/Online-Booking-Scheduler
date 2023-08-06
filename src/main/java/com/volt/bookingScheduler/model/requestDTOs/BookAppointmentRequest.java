package com.volt.bookingScheduler.model.requestDTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookAppointmentRequest implements Serializable {

    @JsonProperty("operator_id")
    private Long operatorId;
    @JsonProperty("start_time")
    private String startTime;
    @JsonProperty("end_time")
    private String endTime;
}

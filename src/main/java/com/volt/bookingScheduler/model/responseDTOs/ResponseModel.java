package com.volt.bookingScheduler.model.responseDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseModel implements Serializable {

    private Integer status;

    private String message;
}

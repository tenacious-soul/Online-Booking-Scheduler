package com.volt.bookingScheduler.service;

import com.volt.bookingScheduler.model.requestDTOs.RescheduleAppointment;
import com.volt.bookingScheduler.model.responseDTOs.ResponseModel;


public interface AppointmentService {

    ResponseModel bookAppointment(Long operatorId, String startTime, String endTime);

    ResponseModel cancelAppointment(Long appointmentId);

    ResponseModel rescheduleAppointment(RescheduleAppointment rescheduleAppointment);
}

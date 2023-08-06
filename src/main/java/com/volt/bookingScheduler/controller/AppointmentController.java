package com.volt.bookingScheduler.controller;

import com.volt.bookingScheduler.model.requestDTOs.BookAppointmentRequest;
import com.volt.bookingScheduler.model.requestDTOs.RescheduleAppointment;
import com.volt.bookingScheduler.model.responseDTOs.ResponseModel;
import com.volt.bookingScheduler.service.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentServiceImpl appointmentService;

    @PostMapping("/bookAppointment")
    public ResponseEntity<ResponseModel> bookAppointment(@RequestBody BookAppointmentRequest bookAppointmentRequest) {
        return ResponseEntity.ok(appointmentService.bookAppointment(bookAppointmentRequest.getOperatorId(), bookAppointmentRequest.getStartTime(), bookAppointmentRequest.getEndTime()));
    }

    @PostMapping("/rescheduleAppointment")
    public ResponseEntity<ResponseModel> rescheduleAppointment(@RequestBody RescheduleAppointment rescheduleAppointment) {
        try {
            return ResponseEntity.ok(appointmentService.rescheduleAppointment(rescheduleAppointment));
        } catch (Exception ex) {
            return ResponseEntity.ok(ResponseModel.builder().status(HttpStatus.BAD_REQUEST.value()).message("Unable to reschedule, please try with a different time slot").build());
        }
    }

    @PutMapping("/cancelAppointment")
    public ResponseEntity<ResponseModel> cancelAppointment(@RequestParam(value = "appointment_id", required = true) Long appointmentId) {
        return ResponseEntity.ok(appointmentService.cancelAppointment(appointmentId));
    }
}

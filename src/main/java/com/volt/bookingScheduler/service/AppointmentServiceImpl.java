package com.volt.bookingScheduler.service;

import com.volt.bookingScheduler.entity.Appointment;
import com.volt.bookingScheduler.model.requestDTOs.RescheduleAppointment;
import com.volt.bookingScheduler.model.responseDTOs.ResponseModel;
import com.volt.bookingScheduler.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments(Long operatorId, LocalDateTime startTime, LocalDateTime endTime) {
        return appointmentRepository.findByStatusAndOperatorIdAndStartTimeBetween(1, operatorId, startTime, endTime);
    }

    @Override
    @Transactional
    public ResponseModel bookAppointment(Long operatorId, String slotStartTime, String slotEndTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.parse(slotStartTime, formatter);
        LocalDateTime endTime = LocalDateTime.parse(slotEndTime, formatter);
        List<Appointment> appointmentList = getAllAppointments(operatorId, startTime, endTime);
        if (!appointmentList.isEmpty()) {
            return ResponseModel.builder().message("Already appointment in the requested time slot").status(HttpStatus.BAD_REQUEST.value()).build();
        }
        Appointment appointment = Appointment.builder().operatorId(operatorId).endTime(endTime).startTime(startTime).status(1).build();
        appointmentRepository.save(appointment);
        return ResponseModel.builder().message("Appointment booked successfully").status(HttpStatus.OK.value()).build();
    }

    @Transactional
    @Override
    public ResponseModel rescheduleAppointment(RescheduleAppointment rescheduleAppointment) {

        Appointment appointment = appointmentRepository.findByAppointmentIdAndStatus(rescheduleAppointment.getAppointmentId(), 1);
        if (appointment == null)
            return ResponseModel.builder().message("No scheduled Appointment Found").status(HttpStatus.NOT_FOUND.value()).build();
        cancelAppointment(rescheduleAppointment.getAppointmentId());
        ResponseModel isBookingPossible = bookAppointment(appointment.getOperatorId(), rescheduleAppointment.getStartTime(), rescheduleAppointment.getEndTime());
        if (isBookingPossible == null || isBookingPossible.getStatus() == HttpStatus.BAD_REQUEST.value())
            throw new RuntimeException();
        return ResponseModel.builder().message("Appointment rescheduled successfully").status(HttpStatus.OK.value()).build();
    }

    @Override
    @Transactional
    public ResponseModel cancelAppointment(Long appointmentId) {
        Integer cancelFlag = appointmentRepository.cancelAppointment(appointmentId);
        if (cancelFlag == 0) {
            return ResponseModel.builder().status(HttpStatus.NOT_FOUND.value()).message("No appointment Found!").build();
        }
        return ResponseModel.builder().message("Appointment cancelled successfully").status(HttpStatus.OK.value()).build();
    }

}

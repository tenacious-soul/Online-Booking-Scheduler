package com.volt.bookingScheduler.service;

import com.volt.bookingScheduler.entity.Appointment;
import com.volt.bookingScheduler.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public List<String> getAllAppointmentsForTheOperator(Long operatorId) {
        List<String> getAllAppointmentList = new ArrayList<>();
        List<Appointment> appointmentList = appointmentRepository.findByOperatorIdAndStatus(operatorId, 1);
        for (Appointment appointment : appointmentList) {
            getAllAppointmentList.add(formatSlot(appointment.getStartTime(), appointment.getEndTime()));
        }
        return getAllAppointmentList;
    }

    public List<String> getMergedOpenSlotsByOperatorAndDate(Long operatorId, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        LocalDate localDate = LocalDate.parse(date, formatter);
        LocalDateTime startDateTime = localDate.atStartOfDay();
        LocalDateTime endDateTime = localDate.atTime(LocalTime.MAX);

        List<Appointment> bookedAppointments = appointmentRepository.findByStatusAndOperatorIdAndStartTimeBetween(1, operatorId, startDateTime, endDateTime);
        List<String> mergedSlots = new ArrayList<>();

        int startHour = 0;
        int i = 0;
        int[] hoursArr = new int[24];
        Arrays.fill(hoursArr, 0);

        for (Appointment appointment : bookedAppointments) {
            hoursArr[appointment.getStartTime().getHour()] = 1;
            hoursArr[appointment.getEndTime().getHour()] = 1;
        }

        while (startHour <= 23 && i <= 23) {
            if (hoursArr[i] == 0) {
                i++;
            } else {
                mergedSlots.add(startHour + "-" + i);
                while (hoursArr[i] != 0) {
                    i++;
                }
                startHour = i;
            }
        }

        mergedSlots.add(startHour + "-" + 24);

        return mergedSlots;
    }

    private String formatSlot(LocalDateTime start, LocalDateTime end) {
        return start.getHour() + "-" + end.getHour();
    }
}

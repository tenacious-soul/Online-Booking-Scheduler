package com.volt.bookingScheduler.repository;

import com.volt.bookingScheduler.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByStatusAndOperatorIdAndStartTimeBetween(Integer status, Long operatorId, LocalDateTime start, LocalDateTime end);

    @Modifying
    @Query(value = "UPDATE Appointment SET status = 5 WHERE appointmentId=:appointmentId")
    Integer cancelAppointment(Long appointmentId);

    Appointment findByAppointmentIdAndStatus(Long appointmentId, Integer status);
}

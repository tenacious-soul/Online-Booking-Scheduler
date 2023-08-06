package com.volt.bookingScheduler.service;

import java.util.List;

public interface OperatorService {

    List<String> getAllAppointmentsForTheOperator(Long operatorId);

    List<String> getMergedOpenSlotsByOperatorAndDate(Long operatorId, String date);
}

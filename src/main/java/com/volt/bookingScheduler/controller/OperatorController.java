package com.volt.bookingScheduler.controller;

import com.volt.bookingScheduler.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/operator")
public class OperatorController {

    @Autowired
    OperatorService operatorService;


    @GetMapping("/getAllAppointments")
    public ResponseEntity<List<String>> getAllAppointments(@RequestParam(value = "operator_id", required = true) Long operatorId) {
        return ResponseEntity.ok(operatorService.getAllAppointmentsForTheOperator(operatorId));
    }

    @GetMapping("/getOpenSlots")
    public ResponseEntity<List<String>> getOpenSlots(@RequestParam(value = "operator_id", required = true) Long operatorId, String date){
        return ResponseEntity.ok(operatorService.getMergedOpenSlotsByOperatorAndDate(operatorId, date));
    }
}

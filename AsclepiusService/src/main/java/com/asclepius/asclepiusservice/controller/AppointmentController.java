package com.asclepius.asclepiusservice.controller;

import com.asclepius.asclepiusservice.model.Appointment;
import com.asclepius.asclepiusservice.model.AppointmentDto;
import com.asclepius.asclepiusservice.repository.AppointmentRepository;
import com.asclepius.asclepiusservice.repository.PhysicianRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AppointmentController {

    private PhysicianRepository physicianRepository;

    @GetMapping("/appointment")
    public List<AppointmentDto> getAllAppointment() {
        List<Appointment> appointments = AppointmentRepository.getAllAppointment();
        return appointments.stream().map(
                appointment -> AppointmentDto.builder()
                        .id(appointment.getId())
                        .date(appointment.getDate())
                        .note(appointment.getNote())
                        .physician(physicianRepository.getPhysicianById(appointment.getPhysicianId()))
                        .build()
        ).collect(Collectors.toList());
    }
}

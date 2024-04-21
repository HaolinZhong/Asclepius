package com.asclepius.asclepiusservice.AItools;

import com.asclepius.asclepiusservice.model.Appointment;
import com.asclepius.asclepiusservice.repository.AppointmentRepository;
import dev.langchain4j.agent.tool.Tool;

import java.util.Date;

public class AppointmentManager {

    @Tool
    public Appointment createNewAppointmentByPhysicianId(int physicianId, String patientConditionSummary) {
        Date nextAvailableDate = AppointmentRepository.getPhysicianNextAvailableDate(physicianId);
        Appointment newAppointment = new Appointment(0, physicianId, nextAvailableDate, patientConditionSummary);
        AppointmentRepository.createAppointment(newAppointment);
        return newAppointment;
    }
}

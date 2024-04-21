package com.asclepius.asclepiusservice.repository;

import com.asclepius.asclepiusservice.model.Appointment;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AppointmentRepository {

    private static final Map<Integer, Appointment> appointmentMap = new ConcurrentHashMap<>();
    private static final AtomicInteger idSequence = new AtomicInteger(0);

    public static Date getPhysicianNextAvailableDate(int physicianId) {
        Date latestDate = appointmentMap.values().stream().filter(appointment -> appointment.getPhysicianId() == physicianId).sorted().map(Appointment::getDate).findFirst().orElse(new Date());
        // not thread safe but this is only a demo...
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(latestDate);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    public static List<Appointment> getAllAppointment() {
        return new ArrayList<>(appointmentMap.values());
    }

    public static void createAppointment(Appointment appointment) {
        appointment.setId(idSequence.getAndIncrement());
        appointmentMap.put(appointment.getId(), appointment);
    }
}

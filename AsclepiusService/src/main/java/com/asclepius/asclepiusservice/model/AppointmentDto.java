package com.asclepius.asclepiusservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class AppointmentDto {
    private int id;
    private Physician physician;
    private Date date;
    private String note;
}

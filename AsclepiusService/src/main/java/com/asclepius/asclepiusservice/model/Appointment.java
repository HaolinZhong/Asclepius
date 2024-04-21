package com.asclepius.asclepiusservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Data
@AllArgsConstructor
public class Appointment implements Comparable<Appointment>{
    private int id;
    private int physicianId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    private String note;

    @Override
    public int compareTo(@NotNull Appointment o) {
        // the latest ranked first
        return -this.date.compareTo(o.getDate());
    }
}

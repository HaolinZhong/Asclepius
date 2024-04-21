package com.asclepius.asclepiusservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Physician {
    private int id;
    private String name;
    private String specialty;
    private String description;
}

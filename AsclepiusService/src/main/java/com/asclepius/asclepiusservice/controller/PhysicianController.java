package com.asclepius.asclepiusservice.controller;

import com.asclepius.asclepiusservice.model.Physician;
import com.asclepius.asclepiusservice.repository.PhysicianRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@AllArgsConstructor
public class PhysicianController {

    PhysicianRepository physicianRepository;

    @GetMapping("/physician")
    public List<Physician> getAllPhysicians() {
        return physicianRepository.getAllPhysician().values().stream().toList();
    }

    @GetMapping("/physician/{id}")
    public Physician getPhysicianById(@PathVariable int id) {
        return physicianRepository.getPhysicianById(id);
    }
}

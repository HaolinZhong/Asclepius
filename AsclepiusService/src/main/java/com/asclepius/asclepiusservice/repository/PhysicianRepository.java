package com.asclepius.asclepiusservice.repository;

import com.asclepius.asclepiusservice.model.Physician;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PhysicianRepository {

    private RestTemplate restTemplate;

    public PhysicianRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${physician.api}")
    private String physicianApi;

    public Map<Integer, Physician> getAllPhysician() {
        List<Physician> physicians = restTemplate.exchange(physicianApi, HttpMethod.GET, null, new ParameterizedTypeReference<List<Physician>>() {}).getBody();
        return physicians.stream().collect(Collectors.toMap(Physician::getId, v->v));
    }

    public Physician getPhysicianById(int physicianId) {
        Map<Integer, Physician> physicianMap = getAllPhysician();
        return physicianMap.get(physicianId);
    }
}

package com.example.grouptransportapi.service;

import com.example.grouptransportapi.dao.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepo;

    public VehicleService(VehicleRepository vehicleRepo) {
        this.vehicleRepo = vehicleRepo;
    }


}

package com.example.grouptransportapi.service;

import com.example.grouptransportapi.bean.Vehicle;
import com.example.grouptransportapi.dao.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepo;
    @Autowired
    public VehicleService(VehicleRepository vehicleRepo) {
        this.vehicleRepo = vehicleRepo;
    }

    public void createVehicle(Vehicle vehicle){
        vehicleRepo.save(vehicle);
    }
    public List<Vehicle> selectVehicles(){
        return vehicleRepo.findAll();
    }
}
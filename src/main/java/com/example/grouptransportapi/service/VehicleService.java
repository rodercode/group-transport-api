package com.example.grouptransportapi.service;

import com.example.grouptransportapi.bean.Crew;
import com.example.grouptransportapi.bean.Vehicle;
import com.example.grouptransportapi.dao.CrewRepository;
import com.example.grouptransportapi.dao.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepo;
    private final CrewRepository crewRepository;
    @Autowired
    public VehicleService(VehicleRepository vehicleRepo, CrewRepository crewRepository) {
        this.vehicleRepo = vehicleRepo;
        this.crewRepository = crewRepository;
    }

    public Optional<Vehicle> selectVehicle(Long vehicleId){
        return vehicleRepo.findById(vehicleId);
    }

    public List<Vehicle> selectVehicles(){
        return vehicleRepo.findAll();
    }
    public List<Vehicle> selectVehiclesByGroupId(Long groupId){
        return vehicleRepo.findAllByGroupId(groupId);
    }

    public void createVehicle(Vehicle vehicle){
        Crew crew = crewRepository.findById(vehicle.getGroupId()).get();
        int vehicles = crew.getVehicle();
        crew.setVehicle(vehicles + 1);
        crewRepository.save(crew);
        vehicleRepo.save(vehicle);
    }

    public void changeVehicleStatus(Vehicle vehicle,boolean status){
        vehicle.setVehicleAvailable(status);
        vehicleRepo.save(vehicle);
    }


    public void removeVehicle(Long groupId){
       Vehicle vehicle = vehicleRepo.findById(groupId).get();
       vehicleRepo.delete(vehicle);
    }
}

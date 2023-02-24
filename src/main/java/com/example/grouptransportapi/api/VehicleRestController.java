package com.example.grouptransportapi.api;

import com.example.grouptransportapi.bean.Vehicle;
import com.example.grouptransportapi.dao.VehicleRepository;
import com.example.grouptransportapi.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleRestController {
    private final VehicleService vehicleService;
    @Autowired
    public VehicleRestController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    @GetMapping("api/vehicles")
    private ResponseEntity<List<Vehicle>> selectAllVehicles(){
        return ResponseEntity.ok(vehicleService.selectVehicles());
    }
    @GetMapping("api/vehicles/{groupId}")
    private ResponseEntity<List<Vehicle>> selectAllVehiclesByGroupId(@PathVariable Long groupId){
        return ResponseEntity.ok(vehicleService.selectVehiclesByGroupId(groupId));
    }
    @PostMapping("api/vehicles")
    private ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle){
        vehicleService.createVehicle(vehicle);
        return new ResponseEntity<>(vehicle, HttpStatus.CREATED);
    }
}

package com.example.grouptransportapi.api;
import com.example.grouptransportapi.bean.Crew;
import com.example.grouptransportapi.bean.Vehicle;
import com.example.grouptransportapi.service.CrewService;
import com.example.grouptransportapi.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("api/groups")
public class CrewRestController {
    private final CrewService groupService;
    private final VehicleService vehicleService;
    @Autowired
    public CrewRestController(CrewService groupService, VehicleService vehicleService) {
        this.groupService = groupService;
        this.vehicleService = vehicleService;
    }
    @GetMapping
    private ResponseEntity<List<Crew>> showAllGroups() {
        return ResponseEntity.ok(groupService.showGroups());
    }
    @PostMapping
    private ResponseEntity<Crew> createGroup(@RequestBody Crew group) {
        groupService.createGroup(group);
        return new ResponseEntity<>(group, HttpStatus.CREATED);
    }
    @PostMapping("{groupId}/members")
    private ResponseEntity<Crew> addMember(@PathVariable Long groupId) {
            Crew group = groupService.addMember(groupId);
            return ResponseEntity.ok(group);
    }
    @DeleteMapping("{groupId}/members")
    private ResponseEntity<Crew> removeMember(@PathVariable Long groupId){
        Crew group = groupService.removeMember(groupId);
        return ResponseEntity.ok(group);
    }
    @GetMapping("vehicles/{groupId}")
    private ResponseEntity<List<Vehicle>> selectAllVehiclesByGroupId(@PathVariable Long groupId){
        return ResponseEntity.ok(vehicleService.selectVehiclesByGroupId(groupId));
    }
    @PostMapping("{groupId}/vehicles")
    private ResponseEntity<Vehicle> addVehicle(@PathVariable Long groupId,@RequestBody Vehicle vehicle){
        System.out.println(vehicle);
        vehicle.setGroupId(groupId);
        vehicleService.createVehicle(vehicle);
        return new ResponseEntity<>(vehicle, HttpStatus.CREATED);
    }
    @DeleteMapping("api/vehicles/{vehicleId}")
    private ResponseEntity<Vehicle> removeVehicle(@PathVariable Long vehicleId){
        Vehicle vehicle = vehicleService.selectVehicle(vehicleId).get();
        vehicleService.removeVehicle(vehicleId);
        return ResponseEntity.ok(vehicle);
    }
}

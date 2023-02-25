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
    private final CrewService crewService;
    private final VehicleService vehicleService;

    @Autowired
    public CrewRestController(CrewService crewService, VehicleService vehicleService) {
        this.crewService = crewService;
        this.vehicleService = vehicleService;
    }
    @GetMapping
    private ResponseEntity<List<Crew>> showAllGroups() {
        return ResponseEntity.ok(crewService.showGroups());
    }
    @PostMapping
    private ResponseEntity<Crew> createGroup(@RequestBody Crew crew) {
        crewService.createGroup(crew);
        return new ResponseEntity<>(crew, HttpStatus.CREATED);
    }
    @DeleteMapping("{groupId}")
    private ResponseEntity<String> deleteGroup(@PathVariable Long groupId) {
        crewService.removeGroup(groupId);
        return ResponseEntity.ok("Group delete successfully");
    }
    @PostMapping("{groupId}/members")
    private ResponseEntity<Crew> addMember(@PathVariable Long groupId) {
        Crew group = crewService.addMember(groupId);
        return ResponseEntity.ok(group);
    }
    @DeleteMapping("{groupId}/members")
    private ResponseEntity<String> removeMember(@PathVariable Long groupId) {
        crewService.removeMember(groupId);
        return ResponseEntity.ok(" Member deleted successfully");
    }
    @GetMapping("{groupId}/vehicles")
    private ResponseEntity<List<Vehicle>> selectAllVehiclesByGroupId(@PathVariable Long groupId) {
        return ResponseEntity.ok(vehicleService.selectVehiclesByGroupId(groupId));
    }
    @PutMapping("{groupId}/vehicles/{vehicleId}/{status}")
    private ResponseEntity<Vehicle> changeVehicleStatus(@PathVariable Long groupId,
                                                        @PathVariable Long vehicleId,
                                                        @PathVariable boolean status) {
        Vehicle vehicle = vehicleService.selectVehicle(vehicleId).get();
        vehicleService.changeVehicleStatus(vehicleId, status);
        return ResponseEntity.ok(vehicle);
    }
    @PostMapping("{groupId}/vehicles")
    private ResponseEntity<Vehicle> addVehicle(@PathVariable Long groupId,
                                               @RequestBody Vehicle vehicle) {
        vehicle.setGroupId(groupId);
        vehicleService.createVehicle(vehicle);
        return new ResponseEntity<>(vehicle, HttpStatus.CREATED);
    }
    @DeleteMapping("{groupId}/vehicles/{vehicleId}")
    private ResponseEntity<String> removeVehicle(@PathVariable Long groupId,@PathVariable Long vehicleId) {
        vehicleService.removeVehicle(vehicleId);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }
}

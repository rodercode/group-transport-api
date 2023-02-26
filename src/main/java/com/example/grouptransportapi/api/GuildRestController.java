package com.example.grouptransportapi.api;
import com.example.grouptransportapi.bean.Guild;
import com.example.grouptransportapi.bean.Vehicle;
import com.example.grouptransportapi.service.GuildService;
import com.example.grouptransportapi.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("api/groups")
public class GuildRestController {
    private final GuildService guildService;

    public GuildRestController(GuildService guildService) {
        this.guildService = guildService;
    }

    @GetMapping
    private ResponseEntity<List<Guild>> showAllGroups() {
        return ResponseEntity.ok(crewService.showGroups());
    }
    @PostMapping
    private ResponseEntity<Guild> createGroup(@RequestBody Guild crew) {
        crewService.createGroup(crew);
        return new ResponseEntity<>(crew, HttpStatus.CREATED);
    }
    @DeleteMapping("{groupId}")
    private ResponseEntity<String> deleteGroup(@PathVariable Long groupId) {
        guildService.removeGuild(groupId);
        return ResponseEntity.ok("Group delete successfully");
    }
    @PostMapping("{groupId}/members")
    private ResponseEntity<Guild> addMember(@PathVariable Long groupId) {
        Guild group = guildService.addMember(groupId);
        return ResponseEntity.ok(group);
    }
    @DeleteMapping("{groupId}/members")
    private ResponseEntity<String> removeMember(@PathVariable Long groupId) {
        guildService.removeMember(groupId);
        return ResponseEntity.ok(" Member deleted successfully");
    }
    @GetMapping("{groupId}/vehicles")
    private ResponseEntity<List<Vehicle>> selectAllVehiclesByGroupId(@PathVariable Long groupId) {
        return ResponseEntity.ok(guildService.selectVehiclesByGroupId(groupId));
    }
    @PutMapping("{groupId}/vehicles/{vehicleId}/{status}/{duration}")
    private ResponseEntity<Vehicle> changeVehicleStatus(@PathVariable boolean status,
                                                        @PathVariable Long vehicleId,
                                                        @PathVariable int duration) {
        Vehicle vehicle = guildService.selectVehicle(vehicleId).get();
        guildService.changeVehicleStatus(vehicleId, status,duration);
        return ResponseEntity.ok(vehicle);
    }
    @PostMapping("{groupId}/vehicles")
    private ResponseEntity<Vehicle> addVehicle(@PathVariable Long groupId,
                                               @RequestBody Vehicle vehicle) {
        vehicle.setGroupId(groupId);
        guildService.createVehicle(vehicle);
        return new ResponseEntity<>(vehicle, HttpStatus.CREATED);
    }
    @DeleteMapping("{groupId}/vehicles/{vehicleId}")
    private ResponseEntity<String> removeVehicle(@PathVariable Long groupId,@PathVariable Long vehicleId) {
        guildService.removeVehicle(vehicleId);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }
}

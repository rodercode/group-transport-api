package com.example.grouptransportapi.api;
import com.example.grouptransportapi.bean.Guild;
import com.example.grouptransportapi.bean.Vehicle;
import com.example.grouptransportapi.service.GuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("api/groups")
public class GuildRestController {
    private final GuildService guildService;
    @Autowired
    public GuildRestController(GuildService guildService) {
        this.guildService = guildService;
    }

    // Necessary endpoints ------------------------------------------------------------

    // Create A Guild
    @PostMapping
    private ResponseEntity<Guild> createGroup(@RequestBody Guild guild) {
        guildService.createGuild(guild);
        return new ResponseEntity<>(guild, HttpStatus.CREATED);
    }

    // Register Member To Guild
    @PostMapping("{groupId}/members")
    private ResponseEntity<Guild> addMember(@PathVariable Long groupId) {
        Guild group = guildService.addMember(groupId);
        return ResponseEntity.ok(group);
    }

    // Register Vehicle To A Group
    @PostMapping("{groupId}/vehicles")
    private ResponseEntity<Vehicle> addVehicle(@PathVariable Long groupId,
                                               @RequestBody Vehicle vehicle) {
        vehicle.setGroupId(groupId);
        guildService.addVehicle(vehicle);
        return new ResponseEntity<>(vehicle, HttpStatus.CREATED);
    }

    // Unregister Vehicle From A Group
    @DeleteMapping("{groupId}/vehicles/{vehicleId}")
    private ResponseEntity<String> removeVehicle(@PathVariable Long groupId, @PathVariable Long vehicleId) {
        guildService.removeVehicle(vehicleId);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }

    // Get All Vehicles From A Group
    @GetMapping("{groupId}/vehicles")
    private ResponseEntity<List<Vehicle>> selectAllVehiclesByGroupId(@PathVariable Long groupId) {
        return ResponseEntity.ok(guildService.selectVehiclesByGroupId(groupId));
    }

    // Change State Of A Group Vehicle
//    @PutMapping("{groupId}/vehicles/{vehicleId}/{status}/{duration}")
//    private ResponseEntity<Vehicle> changeVehicleStatus(@PathVariable boolean status,
//                                                        @PathVariable Long vehicleId,
//                                                        @PathVariable int duration) {
//        Vehicle vehicle = guildService.selectVehicle(vehicleId).get();
//        guildService.(vehicleId, status, duration);
//        return ResponseEntity.ok(vehicle);
//    }

    // Register Group Walk -- Under Development

    // Unregister Group Walk -- Under Development

    // Testing endpoints --------------------------------------------------------

    // Get All Guilds
    @GetMapping
    private ResponseEntity<List<Guild>> showAllGroups() {
        return ResponseEntity.ok(guildService.showGuilds());

    }

    // Delete A Guild
    @DeleteMapping("{groupId}")
    private ResponseEntity<String> deleteGroup(@PathVariable Long groupId) {
        guildService.removeGuild(groupId);
        return ResponseEntity.ok("Group delete successfully");
    }

    // Unregister Member From Guild
    @DeleteMapping("{groupId}/members")
    private ResponseEntity<String> removeMember(@PathVariable Long groupId) {
        guildService.removeMember(groupId);
        return ResponseEntity.ok(" Member deleted successfully");
    }
}

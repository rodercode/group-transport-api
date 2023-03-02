package com.example.grouptransportapi.api;
import com.example.grouptransportapi.RestTempleCrud;
import com.example.grouptransportapi.bean.Guild;
import com.example.grouptransportapi.bean.Trip;
import com.example.grouptransportapi.bean.VehicleInfo;
import com.example.grouptransportapi.service.GuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("guilds")
public class GuildRestController {
    private final GuildService guildService;
    private final RestTemplate restTemplate;
    private  RestTempleCrud restTempleCrud = new RestTempleCrud();
    @Autowired
    public GuildRestController(GuildService guildService, RestTemplate restTemplate) {
        this.guildService = guildService;
        this.restTemplate = restTemplate;

    }

    @GetMapping("/{vehicleId}")
    private Optional<VehicleInfo> vehicle(@PathVariable Long vehicleId){
        return restTempleCrud.getVehicle(restTemplate,vehicleId);
    }
    // Create A Guild
    @PostMapping
    private ResponseEntity<Guild> createGroup(@RequestBody Guild guild) {
        guildService.createGuild(guild);
        return new ResponseEntity<>(guild, HttpStatus.CREATED);
    }
    // Delete A Guild
    @DeleteMapping("{groupId}")
    private ResponseEntity<String> deleteGroup(@PathVariable Long groupId) {
        guildService.removeGuild(groupId);
        return ResponseEntity.ok("Group delete successfully");
    }
    //Register Member To Guild
    @PutMapping("{groupId}/members/register")
    private ResponseEntity<Guild> addMember(@PathVariable Long groupId) {
        Guild guild = guildService.showGuildById(groupId).get();
        guildService.addMember(groupId);
        return ResponseEntity.ok(guild);
    }
    // UnRegister Member To Guild
    @PutMapping("{groupId}/members/unregister")
    private ResponseEntity<Guild> removeMember(@PathVariable Long groupId) {
        Guild guild = guildService.showGuildById(groupId).get();
        guildService.removeMember(groupId);
        return ResponseEntity.ok(guild);
    }
    // Register Vehicle To A Guild
    @PutMapping("/{groupId}/vehicles/{vehicleId}/register")
    private String addVehicle(@PathVariable Long groupId, @PathVariable Long vehicleId) {
        return guildService.addVehicle(groupId,vehicleId);
    }
    // Unregister Vehicle From A Guild
    @PutMapping("{groupId}/vehicles/{vehicleId}/unregister")
    private String removeVehicle(@PathVariable Long groupId,@PathVariable Long vehicleId) {
        return guildService.removeVehicle(groupId,vehicleId);
    }
     //Group Memeber Use Vehicle
    @PutMapping("{guildId}/vehicles/{vehicleId}/route/{routeInfoId}")
    private ResponseEntity<Trip> userVehicle(@PathVariable Long guildId, @PathVariable Long vehicleId,@PathVariable Long routeInfoId){
        return ResponseEntity.ok(guildService.changeStateVehicle(guildId,vehicleId,routeInfoId));
    }

    // Register Guild Walk -- Under Development

    // Unregister Guild Walk -- Under Development

    // Testing endpoints --------------------------------------------------------

    // Get All Guilds
    @GetMapping
    private ResponseEntity<List<Guild>> getGuilds() {
        return ResponseEntity.ok(guildService.getGuilds());
    }
    // Unregister Member From Guild

}

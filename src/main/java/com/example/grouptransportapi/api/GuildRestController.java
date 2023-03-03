package com.example.grouptransportapi.api;
import com.example.grouptransportapi.RestTempleCrud;
import com.example.grouptransportapi.bean.Guild;
import com.example.grouptransportapi.bean.Trip;
import com.example.grouptransportapi.beaninfo.GuildWalkInfo;
import com.example.grouptransportapi.beaninfo.VehicleInfo;
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
    @GetMapping
    private ResponseEntity<List<Guild>> getGuilds() {
        return ResponseEntity.ok(guildService.getGuilds());
    }
    // Create A Guild
    @PostMapping
    private ResponseEntity<Guild> createGroup(@RequestBody Guild guild) {
        guildService.createGuild(guild);
        return new ResponseEntity<>(guild, HttpStatus.CREATED);
    }

    @GetMapping("/{vehicleId}")
    private Optional<VehicleInfo> vehicle(@PathVariable Long vehicleId){
        return restTempleCrud.getVehicle(restTemplate,vehicleId);
    }

    // Delete A Guild
    @DeleteMapping("{guildId}")
    private ResponseEntity<String> deleteGroup(@PathVariable Long guildId) {
        guildService.removeGuild(guildId);
        return ResponseEntity.ok("Group delete successfully");
    }

    //Register Member To Guild
    @PutMapping("{guildId}/members/register")
    private ResponseEntity<Guild> addMember(@PathVariable Long guildId) {
        Guild guild = guildService.getGuildById(guildId).get();
        guildService.addMember(guildId);
        return ResponseEntity.ok(guild);
    }
    // UnRegister Member To Guild
    @PutMapping("{guildId}/members/unregister")
    private ResponseEntity<Guild> removeMember(@PathVariable Long guildId) {
        Guild guild = guildService.getGuildById(guildId).get();
        guildService.removeMember(guildId);
        return ResponseEntity.ok(guild);
    }
    // Register Vehicle To A Guild
    @PutMapping("/{guildId}/vehicles/{vehicleId}/register")
    private String addVehicle(@PathVariable Long guildId, @PathVariable Long vehicleId) {
        return guildService.addVehicle(guildId,vehicleId);
    }
    // Unregister Vehicle From A Guild
    @PutMapping("{guildId}/vehicles/{vehicleId}/unregister")
    private String removeVehicle(@PathVariable Long guildId,@PathVariable Long vehicleId) {
        return guildService.removeVehicle(guildId,vehicleId);
    }
     //Group Memeber Use Vehicle
    @PutMapping("{guildId}/vehicles/{vehicleId}/route/{routeInfoId}")
    private ResponseEntity<Trip> userVehicle(@PathVariable Long guildId, @PathVariable Long vehicleId,@PathVariable Long routeInfoId){
        return ResponseEntity.ok(guildService.changeStateVehicle(guildId,vehicleId,routeInfoId));
    }
    // register Guild Walk
    @PutMapping("{guildId}/guild-walks/register")
    private ResponseEntity<Guild> addGuildWalk(@PathVariable Long guildId,@RequestBody GuildWalkInfo guildWalk){
        guildService.registerGuildWalk(guildId,guildWalk);
        return ResponseEntity.ok(guildService.getGuildById(guildId).get());
    }
    // Unregister Guild Walk -- Under Development
    @PutMapping("{guildId}/guild-walks/unregister")
    private ResponseEntity<Guild> removeGuildWalk(@PathVariable Long guildId){
        guildService.unregisterGuildWalk(guildId);
        return ResponseEntity.ok(guildService.getGuildById(guildId).get());
    }
    // Testing endpoints --------------------------------------------------------

    // Get All Guilds

    // Unregister Member From Guild

}

package com.example.grouptransportapi.service;

import com.example.grouptransportapi.RestTempleCrud;
import com.example.grouptransportapi.bean.Guild;
import com.example.grouptransportapi.bean.RouteInfo;
import com.example.grouptransportapi.bean.VehicleInfo;
import com.example.grouptransportapi.dao.GuildRepository;
import com.example.grouptransportapi.handler.ListEmptyException;
import com.example.grouptransportapi.handler.ResourceNotFoundException;
import com.example.grouptransportapi.handler.UniqueValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class GuildService {
    private final GuildRepository guildRepo;
    private final RestTemplate restTemplate;
    RestTempleCrud restTempleCrud = new RestTempleCrud();

    @Autowired
    public GuildService(GuildRepository guildRepo, RestTemplate restTemplate) {
        this.guildRepo = guildRepo;
        this.restTemplate = restTemplate;
    }

    // Necessary crud methods ------------------------------------------------------------
    public List<RouteInfo> routes() {
        ResponseEntity<List<RouteInfo>> response = restTemplate.exchange(
                "https://transportfinal-transport-service.azuremicroservices.io/routes/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }

    // Create New Guild *
    public void createGuild(Guild guild) {
        if (guildRepo.findByName(guild.getName()) != null)
            throw new UniqueValidationException("There already exist a Group with this name");
        else {
            guildRepo.save(guild);
        }
    }

    // Delete Guild
    public void removeGuild(Long groupId) {
        guildRepo.deleteById(groupId);
    }

    // Get Guild By Id
    public Optional<Guild> showGuildById(Long groupId) {
        return guildRepo.findById(groupId);
    }

    // Get All Guilds
    public List<Guild> getGuilds() {
        if (guildRepo.findAll().isEmpty()) {
            throw new ListEmptyException("Group list is empty");
        } else {
            return guildRepo.findAll();
        }
    }

    // Register New Member To A Guild *
    public void addMember(Long guildId) {
        if (guildRepo.findById(guildId).isEmpty()) {
            throw new ResourceNotFoundException("no group exist with this id");
        } else {
            Guild guild = guildRepo.findById(guildId).get();
            guild.setMembers(guild.getMembers() + 1);
            guildRepo.save(guild);
        }
    }

    // Unregister Member From The Guild
    public void removeMember(Long groupId) {
        if (guildRepo.findById(groupId).isEmpty()) {
            throw new ResourceNotFoundException("no group exist with this id");
        } else {
            Guild group = guildRepo.findById((groupId)).get();
            if (group.getMembers() > 0) {
                group.setMembers(group.getMembers() - 1);
                guildRepo.save(group);
            }
        }
    }

    // Add Vehicle To A Guild *
    public String addVehicle(Long groupId, Long vehicleId) {
        // increase vehicle display
        Guild guild = guildRepo.findById(groupId).get();
        guild.setGroupVehicles(guild.getGroupVehicles() + 1);
        guild.setAvailableVehicles(guild.getAvailableVehicles() + 1);
        guildRepo.save(guild);

        //change groupId

        return restTempleCrud.updateVehicleGroupId(restTemplate, vehicleId, groupId.intValue());
    }

    // Remove Vehicle From A Guild *
    public String removeVehicle(Long groupId, Long vehicleId) {
        // decrease vehicle display
        Guild guild = guildRepo.findById(groupId).get();
        if (guild.getGroupVehicles() > 0) {
            guild.setGroupVehicles(guild.getGroupVehicles() - 1);
            guild.setAvailableVehicles(guild.getAvailableVehicles() - 1);
            guildRepo.save(guild);
        }
        return restTempleCrud.updateVehicleGroupId(restTemplate,
                vehicleId, 0);
    }

    // Change State Of A Guild Vehicle
    public String changeStateVehicle(Long groupId, Long vehicleId) {
        Guild guild = guildRepo.findById(groupId).get();
        if (guild.getAvailableVehicles() > 0) {
            guild.setAvailableVehicles(guild.getAvailableVehicles() - 1);
            guildRepo.save(guild);
        }
        return restTempleCrud.updateVehicleStatus(
                restTemplate,
                vehicleId,
                groupId.intValue());
    }
}

package com.example.grouptransportapi.service;

import com.example.grouptransportapi.RestTempleCrud;
import com.example.grouptransportapi.bean.Guild;
import com.example.grouptransportapi.bean.RouteInfo;
import com.example.grouptransportapi.bean.Trip;
import com.example.grouptransportapi.bean.VehicleInfo;
import com.example.grouptransportapi.dao.GuildRepository;
import com.example.grouptransportapi.handler.ListEmptyException;
import com.example.grouptransportapi.handler.ResourceNotFoundException;
import com.example.grouptransportapi.handler.UniqueValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
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
//    public List<RouteInfo> routes() {
//        ResponseEntity<List<RouteInfo>> response = restTemplate.exchange(
//                "https://microservice-enskild-trafik-enskild-trafik.azuremicroservices.io/routes/car",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<>() {
//                });
//        return response.getBody();
//    }

    // Create New Guild
    public void createGuild(Guild guild) {
        if (guildRepo.findByName(guild.getName()) != null)
            throw new UniqueValidationException("There already exist a Group with this name");
        else {
            guildRepo.save(guild);
        }
    }

    // Delete Guild
    public void removeGuild(Long guildId) {
        if (guildRepo.findById(guildId).isEmpty())
            throw new ResourceNotFoundException("Guild does not exist by this id");
        guildRepo.deleteById(guildId);
    }

    // Get Guild By ID
    public Optional<Guild> showGuildById(Long groupId) {
        if (guildRepo.findById(groupId).isEmpty())
            throw new ResourceNotFoundException("Guild with this id does not exist");
        return guildRepo.findById(groupId);
    }

    // Get All Guilds
    public List<Guild> getGuilds() {
        if (guildRepo.findAll().isEmpty()) {
            throw new ListEmptyException("Guild there are no Guilds in the database");
        } else {
            return guildRepo.findAll();
        }
    }

    // Register New Member To A Guild *
    public void addMember(Long guildId) {
        Guild guild = guildRepo.findById(guildId).get();
        guild.setMembers(guild.getMembers() + 1);
        guildRepo.save(guild);
    }

    // Unregister Member From The Guild
    public void removeMember(Long groupId) {
        if (guildRepo.findById(groupId).isEmpty()) {
            throw new ResourceNotFoundException("no guild exist with this id");
        } else {
            Guild group = guildRepo.findById((groupId)).get();
            if (group.getMembers() > 0) {
                group.setMembers(group.getMembers() - 1);
                guildRepo.save(group);
            }
        }
    }

    // Add Vehicle To A Guild *
    public String addVehicle(Long guildId, Long vehicleId) {
        if (guildRepo.findById(guildId).isEmpty())
            throw new ResourceNotFoundException("no Guild exist with this id");
        else {
            // increase vehicle display in a guild
            Guild guild = guildRepo.findById(guildId).get();
            guild.setGroupVehicles(guild.getGroupVehicles() + 1);
            guild.setAvailableVehicles(guild.getAvailableVehicles() + 1);
            guildRepo.save(guild);
            return restTempleCrud.updateVehicleGroupId(restTemplate, vehicleId, guildId.intValue());
        }
    }
        // Remove Vehicle From A Guild *
        public String removeVehicle (Long guildId, Long vehicleId){
            if (guildRepo.findById(guildId).isEmpty())
                throw new ResourceNotFoundException("no guild exist with this id");
            else {
                // decrease vehicle display in a guild
                Guild guild = guildRepo.findById(guildId).get();
                if (guild.getGroupVehicles() > 0) {
                    guild.setGroupVehicles(guild.getGroupVehicles() - 1);
                    guild.setAvailableVehicles(guild.getAvailableVehicles() - 1);
                    guildRepo.save(guild);

                }
                return restTempleCrud.updateVehicleGroupId(restTemplate, vehicleId, guildId.intValue());
            }
        }

        // Change State Of A Guild Vehicle
        public Trip changeStateVehicle (Long guildId, Long vehicleInfoId, Long routeInfoId){
            if (guildRepo.findById(guildId).isEmpty())
                throw new ResourceNotFoundException("no guild exist with this id");
            else {
                Guild guild = guildRepo.findById(guildId).get();
                if (guild.getAvailableVehicles() > 0) {
                    guild.setAvailableVehicles(guild.getAvailableVehicles() - 1);
                    guildRepo.save(guild);
                }
                try {
                    RouteInfo routeInfo = restTempleCrud.getRoutes(restTemplate).get(routeInfoId.intValue()-1);
                    restTempleCrud.updateVehicleStatus(
                            restTemplate,
                            vehicleInfoId,
                            routeInfo.getTravelTime());
                    Optional<VehicleInfo> vehicleInfo = restTempleCrud.getVehicle(restTemplate, vehicleInfoId);
                    return new Trip(vehicleInfo.get(), routeInfo);
                }catch (IndexOutOfBoundsException e){
                    throw new ResourceNotFoundException("no route with this id");
                }
            }
        }

        // Register Guild Walk
        public void registerGuildWalk(Long guildId){
            Guild guild = guildRepo.findById(guildId).get();
            int guildWalks = guild.getGuildWalk();
            guild.setGuildWalk(guildWalks + 1);
        }

}

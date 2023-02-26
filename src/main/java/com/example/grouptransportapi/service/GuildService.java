package com.example.grouptransportapi.service;

import com.example.grouptransportapi.bean.Guild;
import com.example.grouptransportapi.bean.Vehicle;
import com.example.grouptransportapi.dao.GuildRepository;
import com.example.grouptransportapi.dao.VehicleRepository;
import com.example.grouptransportapi.handler.ListEmptyException;
import com.example.grouptransportapi.handler.ResourceNotFoundException;
import com.example.grouptransportapi.handler.UniqueValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuildService {
    private final GuildRepository guildRepo;
    private final VehicleRepository vehicleRepo;

    @Autowired
    public GuildService(GuildRepository guildRepo, VehicleRepository vehicleRepo) {
        this.guildRepo = guildRepo;
        this.vehicleRepo = vehicleRepo;
    }

    // Necessary crud methods ------------------------------------------------------------

    // Create New Guild *
    public void createGuild(Guild crew) {
        if (guildRepo.findByName(crew.getName()) != null)
            throw new UniqueValidationException("There already exist a Group with this name");
        else {
            guildRepo.save(crew);
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
    // Add Vehicle To A Guild *
    public void addVehicle(Vehicle vehicle, Long guildId) {
        // set groupId to a vehicle and get guild by id
        vehicle.setGroupId(guildId);
        Guild guild = guildRepo.findById(guildId).get();

        // increase vehicles display in the group
        int vehicles = guild.getVehicle();
        guild.setVehicle(vehicles + 1);

        vehicleRepo.save(vehicle);
        guildRepo.save(guild);
    }

    // Remove Vehicle From A Guild *
    public void removeVehicle(Long groupId) {
        if (guildRepo.findById(groupId).isEmpty()) {
            throw new ResourceNotFoundException("no group exist with this id");
        } else {
            Guild guild = guildRepo.findById(groupId).get();
            int vehicles = guild.getVehicle();
            guild.setVehicle(vehicles - 1);
            guildRepo.save(guild);
        }
    }

    // Get All Vehicles From A Guild
    public List<Vehicle> selectVehiclesByGroupId(Long groupId) {
        return vehicleRepo.findAllByGroupId(groupId);
    }

    // Change State Of A Guild Vehicle -- Under Development


    // Testing Crud Methods--------------------------------------------------------

    // Show All Groups
    public List<Guild> showGuilds() {
        if (guildRepo.findAll().isEmpty()) {
            throw new ListEmptyException("Group list is empty");
        } else {
            return guildRepo.findAll();
        }
    }

    // Remove Member From The Guild
    public Guild removeMember(Long groupId) {
        if (guildRepo.findById(groupId).isEmpty()) {
            throw new ResourceNotFoundException("no group exist with this id");
        } else {
            Guild group = guildRepo.findById((groupId)).get();
            int members = group.getMembers();
            group.setMembers(members - 1);
            guildRepo.save(group);
            return group;
        }
    }

    // Show Guild By id
    public Optional<Guild> showGuildById(Long groupId) {
        return guildRepo.findById(groupId);
    }

    // Delete Guild
    public void removeGuild(Long groupId) {
        guildRepo.deleteById(groupId);
    }

    // select a vehicle by id
    public Optional<Vehicle> selectVehicle(Long vehicleId) {
        return vehicleRepo.findById(vehicleId);
    }

    // select all vehicles
    public List<Vehicle> selectVehicles() {
        return vehicleRepo.findAll();
    }
}

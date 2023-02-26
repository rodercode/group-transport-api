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

    // Create New Group *
    public void createGuild(Guild crew) {
        if (guildRepo.findByName(crew.getName()) != null)
            throw new UniqueValidationException("There already exist a Group with this name");
        else {
            guildRepo.save(crew);
        }
    }

    // Add New Member To The Group *
    public Guild addMember(Long groupId) {
        if (guildRepo.findById(groupId).isEmpty()) {
            throw new ResourceNotFoundException("no group exist with this id");
        } else {
            Guild guild = guildRepo.findById(groupId).get();
            int members = guild.getMembers();
            guild.setMembers(members + 1);
            guildRepo.save(guild);
            return guild;
        }
    }

    // Add Vehicle To a Group *
    public void addVehicle(Vehicle vehicle) {
        Guild crew = guildRepo.findById(vehicle.getGroupId()).get();
        int vehicles = crew.getVehicle();
        crew.setVehicle(vehicles + 1);
        vehicle.setGroupId(crew.getId());
        guildRepo.save(crew);
        vehicleRepo.save(vehicle);
    }

    // Remove Vehicle From a Group *
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

    // show vehicles that belong to a Group *
    public List<Vehicle> selectVehiclesByGroupId(Long groupId) {
        return vehicleRepo.findAllByGroupId(groupId);
    }

    // Show All Groups test
    public List<Guild> showGuilds() {
        if (guildRepo.findAll().isEmpty()) {
            throw new ListEmptyException("Group list is empty");
        } else {
            return guildRepo.findAll();
        }
    }


    // Test -------- crud method ---------------------------- Test
    // Remove Member From The Group
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

    // Show Group By id
    public Optional<Guild> showGuildById(Long groupId) {
        return guildRepo.findById(groupId);
    }

    // Delete Group
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

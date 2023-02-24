package com.example.grouptransportapi.service;
import com.example.grouptransportapi.bean.Crew;
import com.example.grouptransportapi.dao.CrewRepository;
import com.example.grouptransportapi.handler.ListEmptyException;
import com.example.grouptransportapi.handler.ResourceNotFoundException;
import com.example.grouptransportapi.handler.UniqueValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CrewService {
    private final CrewRepository groupRepo;

    @Autowired
    public CrewService(CrewRepository groupRepo) {
        this.groupRepo = groupRepo;
    }
    // Show All Groups
    public List<Crew> showGroups() {
        if (groupRepo.findAll().isEmpty()) {
            throw new ListEmptyException("Group list is empty");
        } else {
            return groupRepo.findAll();
        }
    }
// Show specific Group by id
    public Optional<Crew> showGroupById(Long groupId) {
        return groupRepo.findById(groupId);
    }
    // Create New Group
    public void createGroup(Crew group) {
        if (groupRepo.findByName(group.getName()) != null) {
            throw new UniqueValidationException("There already exist a Group with this name");
        } else {
            groupRepo.save(group);
        }
    }
    // Add New Member To The Group
    public Crew addMember(Long groupId) {
        if (groupRepo.findById(groupId).isEmpty()) {
            throw new ResourceNotFoundException("no group exist with this id");
        } else {
            Crew group = showGroupById(groupId).get();
            int members = group.getMembers();
            group.setMembers(members + 1);
            groupRepo.save(group);
            return group;
        }
    }
    // Remove Member From The Group
    public Crew removeMember(Long groupId) {
        if (groupRepo.findById(groupId).isEmpty()) {
            throw new ResourceNotFoundException("no group exist with this id");
        } else {
            Crew group = showGroupById(groupId).get();
            int members = group.getMembers();
            group.setMembers(members - 1 );
            groupRepo.save(group);
            return group;
        }
    }
    // Add Vehicle To Group
    public Crew addVehicle(Long groupId){
        if (groupRepo.findById(groupId).isEmpty()) {
            throw new ResourceNotFoundException("no group exist with this id");
        } else {
            Crew group = showGroupById(groupId).get();
            int vehicle = group.getVehicle();
            group.setVehicle(vehicle + 1);
            groupRepo.save(group);
            return group;
        }
    }
    // Remove Vehicle From Group
    public Crew removeVehicle(Long groupId) {
        if (groupRepo.findById(groupId).isEmpty()) {
            throw new ResourceNotFoundException("no group exist with this id");
        } else {
            Crew group = showGroupById(groupId).get();
            int vehicles = group.getVehicle();
            group.setVehicle(vehicles - 1 );
            groupRepo.save(group);
            return group;
        }
    }
}

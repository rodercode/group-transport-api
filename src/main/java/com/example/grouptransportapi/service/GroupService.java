package com.example.grouptransportapi.service;

import com.example.grouptransportapi.bean.Group;
import com.example.grouptransportapi.bean.Vehicle;
import com.example.grouptransportapi.dao.GroupRepository;
import com.example.grouptransportapi.handler.ListEmptyException;
import com.example.grouptransportapi.handler.ResourceNotFoundException;
import com.example.grouptransportapi.handler.UniqueValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepository groupRepo;

    @Autowired
    public GroupService(GroupRepository groupRepo) {
        this.groupRepo = groupRepo;
    }
    // show all Groups
    public List<Group> showGroups() {
        if (groupRepo.findAll().isEmpty()) {
            throw new ListEmptyException("Group list is empty");
        } else {
            return groupRepo.findAll();
        }
    }
// Show specific Group by id
    public Optional<Group> showGroupById(Long groupId) {
        return groupRepo.findById(groupId);
    }

    public void createGroup(Group group) {
        if (groupRepo.findByName(group.getName()) != null) {
            throw new UniqueValidationException("There already exist a Group with this name");
        } else {
            groupRepo.save(group);
        }
    }
    // Add new Member to a Group
    public Group addMember(Long groupId) {
        if (groupRepo.findById(groupId).isEmpty()) {
            throw new ResourceNotFoundException("no group exist with this id");
        } else {
            Group group = showGroupById(groupId).get();
            int members = group.getMembers();
            group.setMembers(members + 1);
            groupRepo.save(group);
            return group;
        }
    }
    public Group removeMember(Long groupId) {
        if (groupRepo.findById(groupId).isEmpty()) {
            throw new ResourceNotFoundException("no group exist with this id");
        } else {
            Group group = showGroupById(groupId).get();
            int members = group.getMembers();
            group.setMembers(members - 1 );
            groupRepo.save(group);
            return group;
        }
    }

    public Group addVehicle(Long groupId){
        if (groupRepo.findById(groupId).isEmpty()) {
            throw new ResourceNotFoundException("no group exist with this id");
        } else {
            Group group = showGroupById(groupId).get();
            int vehicle = group.getVehicle();
            group.setVehicle(vehicle + 1);
            groupRepo.save(group);
            return group;
        }
    }
    
    public Group removeVehicle(Long groupId) {
        if (groupRepo.findById(groupId).isEmpty()) {
            throw new ResourceNotFoundException("no group exist with this id");
        } else {
            Group group = showGroupById(groupId).get();
            int vehicles = group.getVehicle();
            group.setVehicle(vehicles - 1 );
            groupRepo.save(group);
            return group;
        }
    }




}

package com.example.grouptransportapi.service;

import com.example.grouptransportapi.bean.Group;
import com.example.grouptransportapi.dao.GroupRepository;
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
    public List<Group> showGroups(){
        return groupRepo.findAll();
    }
    public Optional<Group> showGroupById(Long groupId){
        return groupRepo.findById(groupId);
    }

    public Group showGroupByName(String name){
        return groupRepo.findByName(name);
    }

    public void createGroup(Group group){
        groupRepo.save(group);
    }

    public Group addMember(Long groupId){
        Group group = showGroupById(groupId).get();
        int members = group.getMembers();
        group.setMembers(members + 1);
        groupRepo.save(group);
        return group;
    }


}

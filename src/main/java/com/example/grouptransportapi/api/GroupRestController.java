package com.example.grouptransportapi.api;

import com.example.grouptransportapi.bean.Group;
import com.example.grouptransportapi.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupRestController {
    private final GroupService groupService;

    @Autowired
    public GroupRestController(GroupService groupService) {
        this.groupService = groupService;
    }
    @GetMapping("api/groups")
    private List<Group> showAllGroups(){
       return groupService.showGroups();
    }
    @PostMapping("api/groups")
    private String createGroup(Group group){
        groupService.createGroup(group);
        return group.getName()+ "was created";
    }
    @PutMapping("api/groups/{groupId}")
    private void addMember(@PathVariable Long groupId){
        groupService.addMember(groupId);
    }
}

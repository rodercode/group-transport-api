package com.example.grouptransportapi.api;

import com.example.grouptransportapi.bean.Group;
import com.example.grouptransportapi.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private ResponseEntity<List<Group>> showAllGroups(){
        if (groupService.showGroups().isEmpty()){
            return ResponseEntity
                    .status(204)
                    .header("x-information", "The list was empty")
                    .build();
        }
       return ResponseEntity.ok(groupService.showGroups());
    }
    @PostMapping("api/groups")
    private ResponseEntity<Group> createGroup(@RequestBody Group group){
        groupService.createGroup(group);
        return new ResponseEntity<>(group, HttpStatus.CREATED);
    }
    @PutMapping("api/groups/{groupId}")
    private ResponseEntity<Group> addMember(@PathVariable Long groupId){
        Group group = groupService.addMember(groupId);
       return ResponseEntity.ok(group);
    }
}

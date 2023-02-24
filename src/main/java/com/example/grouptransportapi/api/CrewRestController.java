package com.example.grouptransportapi.api;

import com.example.grouptransportapi.bean.Crew;
import com.example.grouptransportapi.service.CrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CrewRestController {
    private final CrewService groupService;
    @Autowired
    public CrewRestController(CrewService groupService) {
        this.groupService = groupService;
    }
    @GetMapping("api/groups")
    private ResponseEntity<List<Crew>> showAllGroups() {
        return ResponseEntity.ok(groupService.showGroups());
    }
    @PostMapping("api/groups")
    private ResponseEntity<Crew> createGroup(@RequestBody Crew group) {
        groupService.createGroup(group);
        return new ResponseEntity<>(group, HttpStatus.CREATED);
    }
    @PutMapping("api/groups/{groupId}/members")
    private ResponseEntity<Crew> addMember(@PathVariable Long groupId) {
            Crew group = groupService.addMember(groupId);
            return ResponseEntity.ok(group);
    }
    @DeleteMapping("api/groups/{groupId}/members")
    private ResponseEntity<Crew> removeMember(@PathVariable Long groupId){
        Crew group = groupService.removeMember(groupId);
        return ResponseEntity.ok(group);
    }

    @PutMapping("api/groups/{groupId}/vehicles")
    private ResponseEntity<Crew> addVehicle(@PathVariable Long groupId){
        Crew group = groupService.addVehicle(groupId);
        return ResponseEntity.ok(group);
    }
    @DeleteMapping("api/groups/{groupId}/vehicles")
    private ResponseEntity<Crew> removeVehicle(@PathVariable Long groupId){
        Crew group = groupService.removeVehicle(groupId);
        return ResponseEntity.ok(group);
    }
}

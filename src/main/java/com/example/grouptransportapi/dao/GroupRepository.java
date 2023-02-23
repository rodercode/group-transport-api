package com.example.grouptransportapi.dao;

import com.example.grouptransportapi.bean.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}

package com.example.grouptransportapi.dao;
import com.example.grouptransportapi.bean.Walk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Walk,Long> {
}

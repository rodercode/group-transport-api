package com.example.grouptransportapi.dao;

import com.example.grouptransportapi.bean.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}

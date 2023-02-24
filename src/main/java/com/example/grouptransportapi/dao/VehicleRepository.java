package com.example.grouptransportapi.dao;

import com.example.grouptransportapi.bean.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}

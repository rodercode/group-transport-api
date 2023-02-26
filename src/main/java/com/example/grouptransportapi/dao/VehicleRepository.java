package com.example.grouptransportapi.dao;
import com.example.grouptransportapi.bean.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    public List<Vehicle> findAllByGroupId(Long groupId);
}

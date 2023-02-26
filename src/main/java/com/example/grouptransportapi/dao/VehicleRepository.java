package com.example.grouptransportapi.dao;
import com.example.grouptransportapi.bean.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    public List<Vehicle> findAllByGroupId(Long groupId);
}

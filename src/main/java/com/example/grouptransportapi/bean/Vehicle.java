package com.example.grouptransportapi.bean;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
@Entity(name = "vehicles")
public class Vehicle {

    public Vehicle(String type, String location, boolean isVehicleAvailable) {
        this.type = type;
        this.location = location;
        this.isVehicleAvailable = isVehicleAvailable;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "location",nullable = false)
    private String location;

    @Column(name = "available")
    private boolean isVehicleAvailable;

    @Column(name = "group_id")
    private Long groupId;
}

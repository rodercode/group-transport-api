package com.example.grouptransportapi.bean;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
@Entity(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "location",nullable = false)
    private String location;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "available")
    private boolean isVehicleAvailable;
}

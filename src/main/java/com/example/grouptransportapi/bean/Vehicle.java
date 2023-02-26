package com.example.grouptransportapi.bean;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Setter @Getter
@Entity(name = "group_vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "location",nullable = false)
    private String location;

    @Column(name = "available")
    private boolean available = true;

    @Column(name = "available_in", nullable = false)
    private int availableIn;

    @Column(name = "group_id")
    private Long groupId;
}

package com.example.grouptransportapi.bean;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Guild {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "members", nullable = false)
    private int members;

    @Column(name = "group_vehicles",nullable = false)
    private int groupVehicles;

    @Column(name = "available_vehicles",nullable = false)
    private int availableVehicles;
}

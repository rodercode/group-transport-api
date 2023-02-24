package com.example.grouptransportapi.bean;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "groups", uniqueConstraints = {
         @UniqueConstraint(name = "group_name_unique", columnNames = "name")
})
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "memebers", nullable = false)
    private int members;

    @Column(name = "vehicles",nullable = false)
    private int vehicle;
}

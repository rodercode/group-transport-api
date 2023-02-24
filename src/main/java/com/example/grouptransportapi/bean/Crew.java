package com.example.grouptransportapi.bean;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter @Setter
@Entity
public class Crew {
    @Id
    @GeneratedValue

    private Long id;

    private String name;

    private int members;

    private int vehicle;
}

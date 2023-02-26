package com.example.grouptransportapi.bean;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
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

    @Column(name = "number_of_vehicles")
    private int vehicle;
}

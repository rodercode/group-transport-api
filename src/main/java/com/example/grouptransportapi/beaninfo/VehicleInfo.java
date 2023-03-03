package com.example.grouptransportapi.beaninfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class VehicleInfo {
    private Long id;
    private String type;
    private String location;
    private boolean available;
    private int availableIn;
    private Long groupId;
}

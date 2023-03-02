package com.example.grouptransportapi.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    private  VehicleInfo vehicleInfo;
    private RouteInfo routeInfo;
}

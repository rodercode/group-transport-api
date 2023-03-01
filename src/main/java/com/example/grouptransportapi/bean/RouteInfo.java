package com.example.grouptransportapi.bean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RouteInfo {
    private long id;
    private String startLocation;
    private String endLocation;
    private int travelTime;
}

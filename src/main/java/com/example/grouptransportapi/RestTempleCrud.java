package com.example.grouptransportapi;

import com.example.grouptransportapi.bean.VehicleInfo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class RestTempleCrud {

    public String updateVehicleGroupId(RestTemplate restTemplate,Long vehicleId,int groupId){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<VehicleInfo> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(
                "http://localhost:8081/vehicles/"
                        + vehicleId + "/groups/" + groupId,
                HttpMethod.PUT, entity, String.class).getBody();
    }

    public String updateVehicleStatus(RestTemplate restTemplate, Long vehicleId, int groupId){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<VehicleInfo> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(
                "http://localhost:8081/vehicles/" + vehicleId + "/state/" + false,
                HttpMethod.PUT, entity, String.class).getBody();
    }
}

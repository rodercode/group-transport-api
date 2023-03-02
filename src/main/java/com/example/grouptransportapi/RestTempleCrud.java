package com.example.grouptransportapi;
import com.example.grouptransportapi.bean.RouteInfo;
import com.example.grouptransportapi.bean.VehicleInfo;
import com.example.grouptransportapi.handler.ResourceNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RestTempleCrud {

    public String updateVehicleGroupId(RestTemplate restTemplate,Long vehicleId,int groupId){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<VehicleInfo> entity = new HttpEntity<>(headers);

        try {
            return restTemplate.exchange(
                    "http://localhost:8081/vehicles/"
                            + vehicleId + "/groups/" + groupId,
                    HttpMethod.PUT, entity, String.class).getBody();

        }catch (HttpClientErrorException.NotFound e){
            throw new ResourceNotFoundException("No vehicle exist with this id");
        }
    }

    public void updateVehicleStatus(RestTemplate restTemplate, Long vehicleId, int time){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<VehicleInfo> entity = new HttpEntity<>(headers);

            restTemplate.exchange(
                    "http://localhost:8081/vehicles/" + vehicleId + "/state/" + false+"/duration/"+ time ,
                    HttpMethod.PUT, entity, String.class).getBody();
        }catch (Exception e){
            throw new ResourceNotFoundException("no vehicle exist with this id");
        }

    }


    public List<RouteInfo> getRoutes(RestTemplate restTemplate){
            ResponseEntity<List<RouteInfo>> response = restTemplate.exchange(
                    "https://microservice-enskild-trafik-enskild-trafik.azuremicroservices.io/routes/car",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    });
            return response.getBody();
    }

    public List<VehicleInfo> getVehicles(RestTemplate restTemplate){
        ResponseEntity<List<VehicleInfo>> response = restTemplate.exchange(
                "http://localhost:8081/vehicles",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }

    public Optional<VehicleInfo> getVehicle(RestTemplate restTemplate, Long vehiclieId){
        ResponseEntity<VehicleInfo> responseEntity = restTemplate
                .getForEntity("http://localhost:8081/vehicles/" + vehiclieId,
                VehicleInfo.class);
        return Optional.ofNullable(responseEntity.getBody());
    }

}

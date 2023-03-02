package com.example.grouptransportapi;
import com.example.grouptransportapi.bean.RouteInfo;
import com.example.grouptransportapi.bean.VehicleInfo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class RestTempleCrud {

    public String updateVehicleGroupId(RestTemplate restTemplate,Long vehicleId,int groupId){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<VehicleInfo> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(
                "https://group-transport-service-vehicle-app.azuremicroservices.io/vehicles/"
                        + vehicleId + "/groups/" + groupId,
                HttpMethod.PUT, entity, String.class).getBody();
    }

    public void updateVehicleStatus(RestTemplate restTemplate, Long vehicleId, int time){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<VehicleInfo> entity = new HttpEntity<>(headers);
        restTemplate.exchange(
                "https://group-transport-service-vehicle-app.azuremicroservices.io/vehicles/" + vehicleId + "/state/" + false+"/duration/"+ time ,
                HttpMethod.PUT, entity, String.class).getBody();
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
                "https://group-transport-service-vehicle-app.azuremicroservices.io/vehicles",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }

    public VehicleInfo getVehicleInfo(RestTemplate restTemplate, Long vehiclieId){
        ResponseEntity<VehicleInfo> responseEntity = restTemplate
                .getForEntity("https://group-transport-service-vehicle-app.azuremicroservices.io/vehicles/" + vehiclieId,
                        VehicleInfo.class);
        return responseEntity.getBody();
    }

}

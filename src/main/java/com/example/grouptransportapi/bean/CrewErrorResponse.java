package com.example.grouptransportapi.bean;

import lombok.*;

import java.time.ZonedDateTime;
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CrewErrorResponse {
    private int status;
    private String message;
    private ZonedDateTime data;
}
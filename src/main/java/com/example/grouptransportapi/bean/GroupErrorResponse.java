package com.example.grouptransportapi.bean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupErrorResponse {
    private int status;
    private String message;
    private ZonedDateTime data;
}

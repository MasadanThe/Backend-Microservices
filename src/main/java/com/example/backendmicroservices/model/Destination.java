package com.example.backendmicroservices.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Destination {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // AUTO_INCREMENT
    private long id;
    private double startLongitude;
    private double startLatitude;
    private double endLongitude;
    private double endLatitude;
    private String owner;

    private String movementType;

}

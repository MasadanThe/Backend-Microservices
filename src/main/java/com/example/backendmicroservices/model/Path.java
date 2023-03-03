package com.example.backendmicroservices.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Path {
    private Destination destination;
    private BigDecimal estimatedArrivalTime;

    private ArrayList<String> steps;

}

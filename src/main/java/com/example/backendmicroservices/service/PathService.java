package com.example.backendmicroservices.service;

import com.example.backendmicroservices.model.Destination;
import com.example.backendmicroservices.model.Path;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class PathService {
    public Path getSinglePath(Destination destination){
        ArrayList<String> steps = new ArrayList<>();
        Path path = new Path();

        String apiUrl = "https://api.openrouteservice.org/v2/directions/";
        if (destination.getMovementType().equals("walk"))
        {
            apiUrl += "foot-walking";
        }
        else if (destination.getMovementType().equals("bicycle")) {
            apiUrl += "cycling-regular";

        }
        else if (destination.getMovementType().equals("car"))
        {
            apiUrl += "driving-car";
        }
        apiUrl += "?api_key=5b3ce3597851110001cf624898101b6703844696b82ca682b050c82e&start=";
        apiUrl += destination.getStartLongitude() + "," + destination.getStartLatitude();
        apiUrl += "&end=" + destination.getEndLongitude() + "," + destination.getEndLatitude();

        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(apiUrl, String.class);
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONObject summary = jsonObject.getJSONArray("features").getJSONObject(0).getJSONObject("properties").getJSONArray("segments").getJSONObject(0);
        JSONArray stepsArray = summary.getJSONArray("steps");
        System.out.println(stepsArray.toString());

        //Looping through the stepsArray and gets the instructions of how to walk
        for (int i = 0; i < stepsArray.length(); i++){
            JSONObject distanceJson = stepsArray.getJSONObject(i);
            JSONObject instructionJson = stepsArray.getJSONObject(i);
            String pathString = "After " + distanceJson.get("distance") + "m " + instructionJson.get("instruction");
            steps.add(pathString);
        }
        path.setDestination(destination);
        path.setSteps(steps);
        path.setEstimatedArrivalTime((BigDecimal) summary.get("duration"));

        return path;
    }
}

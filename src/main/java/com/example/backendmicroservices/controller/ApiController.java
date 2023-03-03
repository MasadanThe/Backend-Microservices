package com.example.backendmicroservices.controller;

import com.example.backendmicroservices.model.CarPath;
import com.example.backendmicroservices.model.Destination;
import com.example.backendmicroservices.model.WalkAndBicyclePath;
import com.example.backendmicroservices.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {
    @Autowired
    private ApiService apiService;

    @PostMapping("walk_path")
    public ResponseEntity<WalkAndBicyclePath> getWalkPath(@RequestBody Destination destination){
        System.out.println(destination.getStartLatitude());
        destination.setMovementType("walk");
        WalkAndBicyclePath walkAndBicyclePath = apiService.getWalkAndBicyclePath(destination);

        return ResponseEntity.ok(walkAndBicyclePath);
    }
    @PostMapping("bicycle_path")
    public ResponseEntity<WalkAndBicyclePath> getBicyclePath(@RequestBody Destination destination){
        System.out.println(destination.getStartLatitude());
        destination.setMovementType("bicycle");
        WalkAndBicyclePath walkAndBicyclePath = apiService.getWalkAndBicyclePath(destination);

        return ResponseEntity.ok(walkAndBicyclePath);
    }
    @PostMapping("car_path")
    public ResponseEntity<CarPath> getCarath(@RequestBody Destination destination){
        System.out.println(destination.getStartLatitude());
        destination.setMovementType("car");
        CarPath carPath = apiService.getCarPath(destination);

        return ResponseEntity.ok(carPath);
    }
    @PostMapping ("save_path")
    public ResponseEntity<Destination> savePath(@RequestBody Destination destination){
        apiService.savePath(destination);

        return ResponseEntity.ok(destination);
    }
    @PostMapping ("get_paths")
    public ResponseEntity<List> getPaths(@RequestParam String owner, @RequestParam String movementType){
        List paths = apiService.getPath(owner, movementType);

        return ResponseEntity.ok(paths);
    }

    @DeleteMapping("delete_path")
    public ResponseEntity<Long> deletePath(@RequestParam Long id)
    {
        apiService.deletePath(id);
        return ResponseEntity.ok(id);
    }

}

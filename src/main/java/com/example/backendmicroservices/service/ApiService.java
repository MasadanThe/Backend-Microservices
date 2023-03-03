package com.example.backendmicroservices.service;

import com.example.backendmicroservices.model.CarPath;
import com.example.backendmicroservices.model.Destination;
import com.example.backendmicroservices.model.Path;
import com.example.backendmicroservices.model.WalkAndBicyclePath;
import com.example.backendmicroservices.repository.APIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ApiService {
    Random random;
    @Autowired
    private APIRepository apiRepository;

    private PathService pathService;

    public ApiService(){
        pathService = new PathService();
        random = new Random();
    }


    public WalkAndBicyclePath getWalkAndBicyclePath(Destination destination){
        WalkAndBicyclePath walkAndBicyclePath = new WalkAndBicyclePath();
        Path path = pathService.getSinglePath(destination);

        walkAndBicyclePath.setPath(path);
        walkAndBicyclePath.setWeather(getWeather());

        return walkAndBicyclePath;
    }

    public CarPath getCarPath(Destination destination)
    {
        CarPath carPath = new CarPath();
        Path path1 = pathService.getSinglePath(destination);
        Path path2 = pathService.getSinglePath(destination);
        carPath.setPath1(path1);
        carPath.setPath2(path2);
        carPath.setDelays(getDelays());

        return carPath;
    }

    public void savePath(Destination destination){
        apiRepository.save(destination);
    }

    public List getPath(String owner, String movementType){
        ArrayList<Destination> destinations = new ArrayList<>();
        List paths = new ArrayList();
        if (movementType.equals("all"))
        {
            destinations = apiRepository.getAllByOwner(owner);
        }
        else {
            destinations = apiRepository.getAllByOwnerAndMovementType(owner, movementType);
        }

        for (Destination destination: destinations) {
            if (destination.getMovementType().equals("walk"))
            {
                paths.add(getWalkAndBicyclePath(destination));
            }
            else if (destination.getMovementType().equals("bicycle"))
            {
                paths.add(getWalkAndBicyclePath(destination));
            }
            else if (destination.getMovementType().equals("car"))
            {
                paths.add(getCarPath(destination));
            }
        }

        return paths;
    }

    public void deletePath(Long id){
        apiRepository.deleteById(id);
    }

    private String getDelays(){
        String delays = "";

        switch (random.nextInt(4))
        {
            case 0:
                delays = "None";
                break;
            case 1:
                delays = "Medium Traffic";
                break;
            case 2:
                delays = "High Traffic";
                break;
            case 3:
                delays = "Accident";
                break;
        }
        return  delays;
    }

    private String getWeather(){
        String weather = "";

        switch (random.nextInt(4))
        {
            case 0:
                weather = "Clouds";
                break;
            case 1:
                weather = "Sun";
                break;
            case 2:
                weather = "Rain";
                break;
            case 3:
                weather = "Fog";
                break;
        }
        return  weather;
    }
}

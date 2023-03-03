package com.example.backendmicroservices.repository;

import com.example.backendmicroservices.model.Destination;
import com.example.backendmicroservices.model.Path;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface APIRepository extends JpaRepository<Destination, Long> {
    public ArrayList<Destination> getAllByOwnerAndMovementType(String owner, String movementType);

    public ArrayList<Destination> getAllByOwner(String owner);
}

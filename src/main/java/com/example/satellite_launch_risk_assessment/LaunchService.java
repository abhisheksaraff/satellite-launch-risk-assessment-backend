package com.example.satellite_launch_risk_assessment;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaunchService {

    @Autowired
    private LaunchRepository launchRespository;

    public List<Launch> allLaunches() {
        return launchRespository.findAll();
    }

    public Optional<Launch> getLaunch(ObjectId id) {
        return launchRespository.findById(id);
    }
}

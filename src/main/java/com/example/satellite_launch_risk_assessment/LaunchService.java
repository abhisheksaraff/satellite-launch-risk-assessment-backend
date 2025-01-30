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

    public Launch postLaunch(
            String satellite_name,
            double orbit_altitude_km,
            double satellite_size_m2,
            double maneuver_compatibility_percent,
            int number_of_simulations) {
        Launch newLaunch = new Launch(satellite_name, orbit_altitude_km, satellite_size_m2, maneuver_compatibility_percent, number_of_simulations);
        launchRespository.insert(newLaunch);

        return newLaunch;
    }

    public Launch updateLaunch(
            String id,
            String satellite_name,
            double orbit_altitude_km,
            double satellite_size_m2,
            double maneuver_compatibility_percent,
            int number_of_simulations) {

        if (launchRespository.existsById(new ObjectId(id))) {
            Launch updatedLaunch = new Launch(satellite_name, orbit_altitude_km, satellite_size_m2, maneuver_compatibility_percent, number_of_simulations);
            updatedLaunch.setId(new ObjectId(id));
            return launchRespository.save(updatedLaunch);
        } else
            throw new RuntimeException("Launch corresponding ID " + id + " not found.");
    }


    public void deleteLaunch(String id) {
        if (launchRespository.existsById(new ObjectId(id)))
            launchRespository.deleteById(new ObjectId(id));
        else
            throw new RuntimeException("Launch corresponding ID " + id + " not found.");
    }
}

package com.example.satellite_launch_risk_assessment;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/launches")
public class LaunchController {

    @Autowired
    private LaunchService launchService;

    @GetMapping
    public ResponseEntity<List<Launch>> getAllLaunches() {
        return new ResponseEntity<List<Launch>>(launchService.allLaunches(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Launch>> getLaunch(@PathVariable ObjectId id) {
        return new ResponseEntity<Optional<Launch>>(launchService.getLaunch(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Launch> postLaunch(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Launch>(
                launchService.postLaunch(
                        payload.get("satellite_name"),
                        Double.parseDouble(payload.get("orbit_altitude_km")),
                        Double.parseDouble(payload.get("satellite_size_m2")),
                        Double.parseDouble(payload.get("maneuver_compatibility_percent")),
                        Double.parseDouble(payload.get("drag_coefficient")),
                        Integer.parseInt(payload.get("number_of_simulations"))
                ), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Launch> updateLaunch(@PathVariable String id, @RequestBody Map<String, String> payload) {
        try {
            return new ResponseEntity<Launch>(launchService.updateLaunch(
                    id,
                    payload.get("satellite_name"),
                    Double.parseDouble(payload.get("orbit_altitude_km")),
                    Double.parseDouble(payload.get("satellite_size_m2")),
                    Double.parseDouble(payload.get("maneuver_compatibility_percent")),
                    Double.parseDouble(payload.get("drag_coefficient")),
                    Integer.parseInt(payload.get("number_of_simulations"))
            ), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaunch(@PathVariable String id) {
        try{
            launchService.deleteLaunch(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
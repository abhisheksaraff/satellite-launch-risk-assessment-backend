package com.example.satellite_launch_risk_assessment;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Document(collection = "launches")
@Data
@Getter
@Setter
public class Launch {
    // user input
    @Id
    private ObjectId id;
    private String satellite_name;
    private String launch_date;
    private double orbit_altitude_km;
    private double satellite_size_m2;
    private double maneuver_compatibility_percent;

    // nasa api
    private double debris_density_km3;
    private double debris_count;
    private double solar_radiation_w_m2;
    private double drag_coefficient;
    private double atmospheric_density_kg_m3;
    private double orbital_velocity_km_s;

    // simulation details
    private int number_of_simulations;
    private int collisions_detected;

    // calculations
    private double collision_risk_percent_before_maneuver;
    private double collision_risk_percent_after_maneuver;
    private double drag_force_n_m2;

    public Launch(
            String satellite_name,
            double orbit_altitude_km,
            double satellite_size_m2,
            double maneuver_compatibility_percent,
            int number_of_simulations) {
        this.satellite_name = satellite_name;
        this.launch_date = LocalDate.now().toString();
        this.orbit_altitude_km = orbit_altitude_km;
        this.satellite_size_m2 = satellite_size_m2;
        this.maneuver_compatibility_percent = maneuver_compatibility_percent;
        this.number_of_simulations = number_of_simulations;

        setDataFromNasaApi();
        setDataFromSimulation();
    }

    private void setDataFromNasaApi(){
        this.debris_density_km3 = 1;
        this.debris_count = 2;
        this.solar_radiation_w_m2 = 3;
        this.drag_coefficient = 4;
        this.atmospheric_density_kg_m3 = 5;
        this.orbital_velocity_km_s = 6;
    }

    private void setDataFromSimulation(){
        this.collisions_detected = 1;
        this.collision_risk_percent_before_maneuver = 2;
        this.collision_risk_percent_after_maneuver = 3;
        this.drag_force_n_m2 = 4;
    }
}
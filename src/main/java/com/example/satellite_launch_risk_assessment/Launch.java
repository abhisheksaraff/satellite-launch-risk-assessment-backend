package com.example.satellite_launch_risk_assessment;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "launches")
@Data
@Getter
@Setter
public class Launch {
    // user input
    @Id
    private ObjectId _id;
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
}
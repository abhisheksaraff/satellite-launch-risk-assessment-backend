package com.example.satellite_launch_risk_assessment;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

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
    private int number_of_simulations;
    private double drag_coefficient;

    // nasa api
    private double debris_density_km3;
    private double solar_radiation_w_m2;
    private double atmospheric_density_kg_m3;
    private double orbital_velocity_km_s;

    // simulation results
    private int collisions_detected;
    private double collision_risk_percent_before_maneuver;
    private double collision_risk_percent_after_maneuver;
    private double drag_force_n_m2;

    public Launch(
            String satellite_name,
            double orbit_altitude_km,
            double satellite_size_m2,
            double maneuver_compatibility_percent,
            double drag_coefficient,
            int number_of_simulations) {
        this.satellite_name = satellite_name;
        this.launch_date = LocalDate.now().toString();
        this.orbit_altitude_km = orbit_altitude_km;
        this.satellite_size_m2 = satellite_size_m2;
        this.maneuver_compatibility_percent = maneuver_compatibility_percent;
        this.drag_coefficient = drag_coefficient;
        this.number_of_simulations = number_of_simulations;

        setDataFromSpaceApi();
        setDataFromRiskSimulation();
    }

    private void setDataFromSpaceApi(){
        SpaceAPIService spaceAPIService = new SpaceAPIService();
        this.debris_density_km3 = spaceAPIService.getDebris_density_km3();
        this.solar_radiation_w_m2 = spaceAPIService.getSolar_radiation_w_m2();
        this.atmospheric_density_kg_m3 = spaceAPIService.getAtmospheric_density_kg_m3();
        this.orbital_velocity_km_s = spaceAPIService.getOrbital_velocity_km_s();
    }

    private void setDataFromRiskSimulation(){
        RiskSimulationService riskSimulationService = new RiskSimulationService(
                number_of_simulations,
                maneuver_compatibility_percent,
                debris_density_km3, satellite_size_m2,
                orbit_altitude_km,
                atmospheric_density_kg_m3,
                orbital_velocity_km_s,
                drag_coefficient,
                solar_radiation_w_m2);
        this.collisions_detected = riskSimulationService.getCollisions_detected();
        this.collision_risk_percent_before_maneuver = riskSimulationService.getCollision_risk_before_maneuver();
        this.collision_risk_percent_after_maneuver = riskSimulationService.getCollision_risk_after_maneuver();
        this.drag_force_n_m2 = riskSimulationService.getDrag_force_n_m2();
    }
}
package com.example.satellite_launch_risk_assessment;

import org.springframework.stereotype.Service;

import java.util.Random;

public class RiskSimulationService {


    private int number_of_simulations;
    private double maneuver_compatibility_percent;
    private double debris_density_km3;
    private double satellite_size_m2;
    private double orbit_altitude_km;
    private double atmospheric_density_kg_m3;
    private double orbital_velocity_km_s;
    private double drag_coefficient;
    private double solar_radiation_w_m2; // Solar radiation (W/m²)

    private double collision_risk_before_maneuver;
    private double collision_risk_after_maneuver;
    private double drag_force_n_m2;
    private int collisions_detected;

    public RiskSimulationService(int number_of_simulations,
                                 double maneuver_compatibility_percent,
                                 double debris_density_km3,
                                 double satellite_size_m2,
                                 double orbit_altitude_km,
                                 double atmospheric_density_kg_m3,
                                 double orbital_velocity_km_s,
                                 double drag_coefficient,
                                 double solar_radiation_w_m2) {
        this.number_of_simulations = number_of_simulations;
        this.maneuver_compatibility_percent = maneuver_compatibility_percent;
        this.debris_density_km3 = debris_density_km3;
        this.satellite_size_m2 = satellite_size_m2;
        this.orbit_altitude_km = orbit_altitude_km;
        this.atmospheric_density_kg_m3 = atmospheric_density_kg_m3;
        this.orbital_velocity_km_s = orbital_velocity_km_s;
        this.drag_coefficient = drag_coefficient;
        this.solar_radiation_w_m2 = solar_radiation_w_m2;
        setDataFromSimulation();
    }

    public double getCollision_risk_before_maneuver() {
        return collision_risk_before_maneuver;
    }

    public double getCollision_risk_after_maneuver() {
        return collision_risk_after_maneuver;
    }

    public double getDrag_force_n_m2() {
        return drag_force_n_m2;
    }
    public int getCollisions_detected() {
        return collisions_detected;
    }

    private double calculateCollisionRiskBeforeManeuver() {
        return (debris_density_km3 * satellite_size_m2 * 100) / orbit_altitude_km;
    }

    private double calculateDragForce() {
        double solar_radiation_impact_factor = 1 + (solar_radiation_w_m2 / 1361); // normalized to the average solar radiation at Earth's surface
        return 0.5 * atmospheric_density_kg_m3 * Math.pow(orbital_velocity_km_s, 2) * satellite_size_m2 * drag_coefficient * solar_radiation_impact_factor;
    }

    private void calculateCollisionsDetected() {
        Random rand = new Random();
        collisions_detected = 0;

        for (int i = 0; i < number_of_simulations; i++) {
            // Generate a random value between 0 and 100
            double randomValue = rand.nextDouble() * 100;

            // If the random value is less than the collision risk after maneuver, count it as a collision
            if (randomValue < collision_risk_after_maneuver) {
                collisions_detected++;
            }
        }
    }

    public void setDataFromSimulation() {
        // Step 1: Calculate the initial collision risk before maneuver
        this.collision_risk_before_maneuver = calculateCollisionRiskBeforeManeuver();

        // Step 2: Adjust the collision risk after applying maneuver capability
        this.collision_risk_after_maneuver = collision_risk_before_maneuver * (1 - maneuver_compatibility_percent / 100.0);

        // Step 3: Calculate the drag force with solar radiation consideration
        this.drag_force_n_m2 = calculateDragForce();
    }
}
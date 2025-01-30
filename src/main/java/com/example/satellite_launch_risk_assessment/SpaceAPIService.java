package com.example.satellite_launch_risk_assessment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SpaceAPIService {
    private double debris_density_km3;
    private double solar_radiation_w_m2;
    private double atmospheric_density_kg_m3;
    private double orbital_velocity_km_s;

    @Value("${api.key}")
    private String NASA_API_KEY;

    public SpaceAPIService() {
        this.debris_density_km3 = 1e-8;
        this.solar_radiation_w_m2 = 1380;
        this.atmospheric_density_kg_m3 = 1.225;
        orbital_velocity_km_s = 7.8;
    }

    public double getDebris_density_km3() {
        return debris_density_km3;
    }

    public double getSolar_radiation_w_m2() {
        return solar_radiation_w_m2;
    }

    public double getAtmospheric_density_kg_m3() {
        return atmospheric_density_kg_m3;
    }

    public double getOrbital_velocity_km_s() {
        return orbital_velocity_km_s;
    }
}
package com.fatih.fegp.residence.domain;

public class Residence {
    private final long nationalId;
    private final String city;
    private final String district;
    private final String neighborhood;
    private final boolean active;

    public Residence(long nationalId, String city, String district, String neighborhood, boolean active) {
        this.nationalId = nationalId;
        this.city = city;
        this.district = district;
        this.neighborhood = neighborhood;
        this.active = active;
    }

    public long getNationalId() {
        return nationalId;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public boolean isActive() {
        return active;
    }
}

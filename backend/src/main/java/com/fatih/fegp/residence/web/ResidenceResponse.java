package com.fatih.fegp.residence.web;

public record ResidenceResponse(
        long residenceId,
        String city,
        String district,
        String neighborhood,
        boolean active
) {
}

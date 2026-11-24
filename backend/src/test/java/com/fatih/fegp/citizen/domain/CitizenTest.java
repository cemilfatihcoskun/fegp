package com.fatih.fegp.citizen.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CitizenTest {
    @Test
    public void givenValidCitizenId_whenValidateCitizenId_thenReturnsTrue() {
        long citizenId = 10000000146L;
        boolean isValid = Citizen.validateCitizenId(citizenId);
        assertTrue(isValid);
    }
}

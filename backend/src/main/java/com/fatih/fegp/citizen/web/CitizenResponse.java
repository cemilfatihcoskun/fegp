package com.fatih.fegp.citizen.web;

import com.fatih.fegp.citizen.domain.Citizen;
import com.fatih.fegp.citizen.domain.CitizenStatus;

public record CitizenResponse(long citizenId, String name, CitizenStatus status) {
    public static CitizenResponse from(Citizen citizen) {
        return new CitizenResponse(citizen.getCitizenId(), citizen.getName(), citizen.getStatus());
    }
}

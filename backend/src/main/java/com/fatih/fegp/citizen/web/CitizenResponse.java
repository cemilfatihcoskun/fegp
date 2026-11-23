package com.fatih.fegp.citizen.web;

import com.fatih.fegp.citizen.domain.Citizen;
import com.fatih.fegp.citizen.domain.CitizenStatus;

public record CitizenResponse(long id, long nationalId, String name, CitizenStatus status) {
    public static CitizenResponse from(Citizen citizen) {
	return new CitizenResponse(citizen.getId(), citizen.getNationalId(), citizen.getName(), citizen.getStatus());
    }
}

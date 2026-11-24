package com.fatih.fegp.citizen.domain;

public interface CitizenRepository {
    Citizen save(Citizen citizen);
    Citizen findByCitizenId(long citizenId);
    boolean existsByCitizenId(long citizenId);
    Citizen removeByCitizenId(long citizenId);
}

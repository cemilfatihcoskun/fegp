package com.fatih.fegp.citizen.domain;

public interface CitizenRepository {
    Citizen findById(long id);
    Citizen findByNationalId(long nationalId);
    boolean existsByNationalId(long nationalId);
    Citizen save(Citizen citizen);
}

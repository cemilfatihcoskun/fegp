package com.fatih.fegp.citizen.application;

import com.fatih.fegp.citizen.domain.Citizen;
import com.fatih.fegp.citizen.domain.CitizenErrors;
import com.fatih.fegp.citizen.domain.CitizenRepository;
import com.fatih.fegp.citizen.domain.CitizenStatus;
import org.springframework.stereotype.Service;

@Service
public class CitizenService {
    private final CitizenRepository citizenRepository;

    public CitizenService(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    public Citizen register(long citizenId, String name, String status) {
        Citizen citizen = new Citizen(citizenId, name, status);
    }

    public Citizen save(Citizen citizen) {
        long citizenId = citizen.getCitizenId();
        if (citizenRepository.existsByCitizenId(citizenId)) {
            throw new CitizenErrors.CitizenAlreadyExists(citizenId);
        }
        return citizenRepository.save(citizen);
    }

    public Citizen findByCitizenId(long citizenId) {
        Citizen citizen = citizenRepository.findByCitizenId(citizenId);
        if (citizen == null) {
            throw new CitizenErrors.CitizenNotFound(citizenId);
        }
        return citizen;
    }

    public Citizen removeByCitizenId(long citizenId) {
        if (!citizenRepository.existsByCitizenId(citizenId)) {
            throw new CitizenErrors.CitizenNotFound(citizenId);
        }
        return citizenRepository.removeByCitizenId(citizenId);
    }

    public Citizen suspend(long citizenId) {
        Citizen citizen = citizenRepository.findByCitizenId(citizenId);
        if (citizen == null) {
            throw new CitizenErrors.CitizenNotFound(citizenId);
        }
        citizen.setStatus(CitizenStatus.SUSPENDED);
        return citizenRepository.save(citizen);
    }

    public Citizen activate(long citizenId) {
        Citizen citizen = citizenRepository.findByCitizenId(citizenId);
        if (citizen == null) {
            throw new CitizenErrors.CitizenNotFound(citizenId);
        }
        citizen.setStatus(CitizenStatus.ACTIVE);
        return citizenRepository.save(citizen);
    }
}


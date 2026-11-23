package com.fatih.fegp.citizen.application;

import org.springframework.stereotype.Service;

import com.fatih.fegp.citizen.domain.Citizen;
import com.fatih.fegp.citizen.domain.CitizenRepository;
import com.fatih.fegp.citizen.domain.CitizenStatus;

@Service
public class CitizenService {
    private final CitizenRepository repository;
    
    public CitizenService(CitizenRepository repository) {
	this.repository = repository;
    }
    
    public Citizen registerCitizen(long nationalId, String name) {
	if (repository.existsByNationalId(nationalId)) {
	    throw new IllegalStateException("Citizen with nationalId already exists.");
	}
	Citizen citizen = Citizen.register(nationalId, name);
	return repository.save(citizen);
    }
    
    public Citizen findById(long id) {
	Citizen citizen = repository.findById(id);
	if (citizen == null) {
	    throw new IllegalArgumentException("Citizen with id is not found.");
	}
	return citizen;
    }

    public Citizen findByNationalId(long nationalId) {
	Citizen citizen = repository.findByNationalId(nationalId);
	if (citizen == null) {
	    throw new IllegalArgumentException("Citizen with id is not found.");
	}
	return citizen;
    }
    
    public Citizen suspend(long id) {
	Citizen citizen = findById(id);
	citizen.suspend();
	return repository.save(citizen);
    }
    
    public Citizen activate(long id) {
	Citizen citizen = findById(id);
	citizen.activate();
	return repository.save(citizen);
    }
}


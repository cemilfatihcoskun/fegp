package com.fatih.fegp.citizen.infrastructure;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.fatih.fegp.citizen.domain.Citizen;
import com.fatih.fegp.citizen.domain.CitizenRepository;

@Repository
public class InMemoryCitizenRepository implements CitizenRepository {
    private final Map<Long, Citizen> byId = new ConcurrentHashMap();
    private final Map<Long, Long> idByNationalId = new ConcurrentHashMap();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public Citizen findById(long id) {
	return byId.get(id);
    }

    @Override
    public Citizen findByNationalId(long nationalId) {
	long id = idByNationalId.get(nationalId);
	return byId.get(id);
    }

    @Override
    public boolean existsByNationalId(long nationalId) {
	return idByNationalId.containsKey(nationalId);
    }

    @Override
    public Citizen save(Citizen citizen) {
	if (citizen == null) {
	    return null;
	}
	long id = 0;
	if (citizen.getId() != 0) {
	    id = citizen.getId();
	} else {
	    id = sequence.getAndIncrement();
	}
	Citizen saved = new Citizen(id, citizen.getNationalId(), citizen.getName(), citizen.getStatus());
	byId.put(id, saved);
	idByNationalId.put(saved.getNationalId(), id);
	return saved;
    }

}

package com.fatih.fegp.citizen.infrastructure;

import com.fatih.fegp.citizen.domain.Citizen;
import com.fatih.fegp.citizen.domain.CitizenErrors;
import com.fatih.fegp.citizen.domain.CitizenRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryCitizenRepository implements CitizenRepository {
    private final Map<Long, Citizen> citizens = new ConcurrentHashMap<Long, Citizen>();

    @Override
    public Citizen save(Citizen citizen) {
        if (citizen == null || !Citizen.validateCitizenId(citizen.getCitizenId())) {
            throw new CitizenErrors.CitizenIllegalArgumentRegister();
        }
        citizens.put(citizen.getCitizenId(), citizen);
        return citizen;
    }

    @Override
    public Citizen findByCitizenId(long citizenId) {
        return citizens.get(citizenId);
    }

    @Override
    public boolean existsByCitizenId(long citizenId) {
        return citizens.containsKey(citizenId);
    }

    @Override
    public Citizen removeByCitizenId(long citizenId) {
        Citizen citizen = citizens.get(citizenId);
        citizens.remove(citizenId);
        return citizen;
    }
}

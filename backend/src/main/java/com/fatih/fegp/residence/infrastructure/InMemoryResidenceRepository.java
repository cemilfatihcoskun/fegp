package com.fatih.fegp.residence.infrastructure;

import com.fatih.fegp.residence.domain.Residence;
import com.fatih.fegp.residence.domain.ResidenceRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryResidenceRepository implements ResidenceRepository {
    Map<Long, Residence> residences = new ConcurrentHashMap<Long, Residence>();

    @Override
    public Residence findByResidenceId(long residenceId) {
        return residences.get(residenceId);
    }
}

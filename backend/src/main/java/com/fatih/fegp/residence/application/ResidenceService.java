package com.fatih.fegp.residence.application;

import com.fatih.fegp.residence.domain.Residence;
import com.fatih.fegp.residence.domain.ResidenceErrors;
import com.fatih.fegp.residence.domain.ResidenceRepository;
import com.fatih.fegp.residence.web.ResidenceResponse;
import org.springframework.stereotype.Service;

@Service
public class ResidenceService {
    private final ResidenceRepository residenceRepository;

    public ResidenceService(ResidenceRepository residenceRepository) {
        this.residenceRepository = residenceRepository;
    }

    public ResidenceResponse findByResidenceId(long residenceId) {
        Residence record = residenceRepository.findByResidenceId(residenceId);
        if (record == null) {
            throw new ResidenceErrors.ResidenceNotFound(residenceId);
        }
        if (!record.isActive()) {
            throw new ResidenceErrors.ResidenceInactive(residenceId);
        }
        return new ResidenceResponse(
                record.getNationalId(),
                record.getCity(),
                record.getDistrict(),
                record.getNeighborhood(),
                record.isActive()
        );
    }
}

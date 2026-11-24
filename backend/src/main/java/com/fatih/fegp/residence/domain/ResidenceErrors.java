package com.fatih.fegp.residence.domain;

import com.fatih.fegp.common.error.domain.BusinessRuleException;
import com.fatih.fegp.common.error.domain.ResourceNotFoundException;

public class ResidenceErrors {
    public static final String RESIDENCE_NOT_FOUND = "RESIDENCE_NOT_FOUND";
    public static final String RESIDENCE_ALREADY_EXISTS = "RESIDENCE_ALREADY_EXISTS";
    public static final String RESIDENCE_INACTIVE = "RESIDENCE_INACTIVE";
    public static final String RESIDENCE_ILLEGAL_ARGUMENT_REGISTER = "RESIDENCE_ILLEGAL_ARGUMENT_REGISTER";

    private ResidenceErrors() {
    }

    public static final class ResidenceNotFound extends ResourceNotFoundException {
        public ResidenceNotFound(long citizenId) {
            super(RESIDENCE_NOT_FOUND, String.format("Residence with residentId %d is not found", citizenId));
        }
    }

    public static final class ResidenceAlreadyExists extends BusinessRuleException {
        public ResidenceAlreadyExists(long citizenId) {
            super(RESIDENCE_ALREADY_EXISTS, String.format("Residence with residentId %d is already exists.", citizenId));
        }
    }

    public static final class ResidenceInactive extends BusinessRuleException {
        public ResidenceInactive(long citizenId) {
            super(RESIDENCE_INACTIVE, String.format("Residence with residentId %d is inactive.", citizenId));
        }
    }

    public static final class ResidenceIllegalArgumentRegister extends BusinessRuleException {
        public ResidenceIllegalArgumentRegister() {
            // TODO: Make a decision about how to show the error
            super(RESIDENCE_ILLEGAL_ARGUMENT_REGISTER, String.format("Invalid arguments to save a residence."));
        }
    }

}

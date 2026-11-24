package com.fatih.fegp.citizen.domain;

import com.fatih.fegp.common.error.domain.BusinessRuleException;
import com.fatih.fegp.common.error.domain.ResourceNotFoundException;

public final class CitizenErrors {
    public static final String CITIZEN_NOT_FOUND = "CITIZEN_NOT_FOUND";
    public static final String CITIZEN_ALREADY_EXISTS = "CITIZEN_ALREADY_EXISTS";
    public static final String CITIZEN_INACTIVE = "CITIZEN_INACTIVE";
    public static final String CITIZEN_ILLEGAL_ARGUMENT_REGISTER = "CITIZEN_ILLEGAL_ARGUMENT_REGISTER";
    public static final String CITIZEN_INVALID_STATUS = "CITIZEN_INVALID_STATUS";
    public static final String CITIZEN_INVALID_CITIZEN_ID = "CITIZEN_INVALID_CITIZEN_ID";
    public static final String CITIZEN_NAME_BLANK = "CITIZEN_NAME_BLANK";

    private CitizenErrors() {
    }

    public static final class CitizenNotFound extends ResourceNotFoundException {
        public CitizenNotFound(long citizenId) {
            super(CITIZEN_NOT_FOUND, String.format("Citizen with nationalId %d is not found", citizenId));
        }
    }

    public static final class CitizenAlreadyExists extends BusinessRuleException {
        public CitizenAlreadyExists(long citizenId) {
            super(CITIZEN_ALREADY_EXISTS, String.format("Citizen with nationalId %d is already exists.", citizenId));
        }
    }

    public static final class CitizenInactive extends BusinessRuleException {
        public CitizenInactive(long citizenId) {
            super(CITIZEN_INACTIVE, String.format("Citizen with nationalId %d is inactive.", citizenId));
        }
    }

    public static final class CitizenIllegalArgumentRegister extends BusinessRuleException {
        public CitizenIllegalArgumentRegister() {
            // TODO: Make a decision about how to show the error
            super(CITIZEN_ILLEGAL_ARGUMENT_REGISTER, String.format("Invalid arguments passed a citizen."));
        }
    }

    public static final class CitizenInvalidStatus extends BusinessRuleException {
        public CitizenInvalidStatus(String status) {
            super(CITIZEN_INVALID_STATUS, String.format("Invalid citizen status %s.", status));
        }
    }

    public static class CitizenInvalidCitizenId extends BusinessRuleException {
        public CitizenInvalidCitizenId(long citizenId) {
            super(CITIZEN_INVALID_CITIZEN_ID, String.format("Invalid citizen id %d.", citizenId));
        }
    }

    public static class CitizenNameBlank extends BusinessRuleException {
        public CitizenNameBlank() {
            super(CITIZEN_NAME_BLANK, "Blank citizen name.");
        }
    }
}

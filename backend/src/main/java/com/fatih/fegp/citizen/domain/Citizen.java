package com.fatih.fegp.citizen.domain;

public class Citizen {
    private final long citizenId;
    private String name;
    private CitizenStatus status;

    public Citizen(long citizenId, String name, CitizenStatus status) {
        if (!Citizen.validateCitizenId(citizenId)) {
            throw new CitizenErrors.CitizenInvalidCitizenId(citizenId);
        }

        this.citizenId = citizenId;
        this.name = name;
        this.status = status;
    }

    public Citizen(long citizenId, String name, String status) {
        if (!Citizen.validateCitizenId(citizenId)) {
            throw new CitizenErrors.CitizenInvalidCitizenId(citizenId);
        }

        this.citizenId = citizenId;
        this.name = name;

        // Check invalid state string or blank string
        try {
            this.status = CitizenStatus.valueOf(status);
        } catch (IllegalArgumentException exception) {
            throw new CitizenErrors.CitizenInvalidStatus(status);
        } catch (NullPointerException exception) {
            throw new CitizenErrors.CitizenInvalidStatus("Blank");
        }
    }

    public final long getCitizenId() {
        return citizenId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isBlank()) {
            throw new CitizenErrors.CitizenNameBlank();
        }
        this.name = name;
    }

    public CitizenStatus getStatus() {
        return status;
    }

    public void setStatus(CitizenStatus status) {
        this.status = status;
    }

    public boolean isActive() {
        return status == CitizenStatus.ACTIVE;
    }

    /**
     * Validates TC National ID by using the official checksum algorithm:
     * <p>
     * 10th digit = (7 * sum(1,3,5,7,9) - sum(2,4,6,8)) % 10 11th digit =
     * sum(1,2,3,4,5,6,7,8,9,10) % 10
     * </p>
     *
     * @param id 11 digit national identity number
     * @return true if the national id is valid
     */
    public static boolean validateCitizenId(long id) {
        if (id < 10_000_000_000L || id > 99_999_999_999L) {
            return false;
        }

        // Starts from the 9th digit
        // Divides the id by 10 in each step
        long oddDigitsSum = 0;
        long evenDigitsSum = 0;
        for (long n = id / 100; n > 0; n /= 10) {
            oddDigitsSum += n % 10;
            n /= 10;
            evenDigitsSum += n % 10;
        }

        // Adding 100 for dismissing negative modulo antics
        long tenthDigitCalculated = (7 * oddDigitsSum - evenDigitsSum + 100) % 10;
        long eleventhDigitCalculated = (oddDigitsSum + evenDigitsSum + tenthDigitCalculated) % 10;

        long tenthDigitActual = (id / 10) % 10;
        long eleventhDigitActual = id % 10;

        return tenthDigitCalculated == tenthDigitActual && eleventhDigitCalculated == eleventhDigitActual;
    }

}

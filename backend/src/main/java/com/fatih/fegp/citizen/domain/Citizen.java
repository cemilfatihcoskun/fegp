package com.fatih.fegp.citizen.domain;

public class Citizen {
    private final long id;
    private final long nationalId;
    private String name;
    private CitizenStatus status;

    public Citizen(long id, long nationalId, String name, CitizenStatus status) {
	this.id = id;
	this.nationalId = nationalId;
	this.name = name;
	this.status = status;
    }

    public final long getId() {
	return id;
    }

    public final long getNationalId() {
	return nationalId;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public CitizenStatus getStatus() {
	return status;
    }

    public boolean isActive() {
	return status == CitizenStatus.ACTIVE;
    }

    public void setStatus(CitizenStatus status) {
	this.status = status;
    }

    public void suspend() {
	if (this.status == CitizenStatus.SUSPENDED) {
	    throw new IllegalStateException("The citizen is already suspended.");
	}
	setStatus(CitizenStatus.SUSPENDED);
    }

    public void activate() {
	if (this.status == CitizenStatus.ACTIVE) {
	    throw new IllegalStateException("The citizen is already active.");
	}
	setStatus(CitizenStatus.ACTIVE);
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
    public boolean validateNationalId(long id) {
	if (id < 10_000_000_000L || id > 99_999_999_999L) {
	    return false;
	    // throw new IllegalArgumentException("National identity number is not valid.");
	}

	// Starts from the 9th digit
	// Divides the id by 10 in each step
	int oddDigitsSum = 0;
	int evenDigitsSum = 0;
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

    public static Citizen register(long nationalId, String name) {
	return new Citizen(0L, nationalId, name, CitizenStatus.ACTIVE);
    }

}

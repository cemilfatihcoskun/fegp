package com.fatih.fegp.citizen.application;

import com.fatih.fegp.citizen.domain.Citizen;
import com.fatih.fegp.citizen.domain.CitizenErrors;
import com.fatih.fegp.citizen.domain.CitizenRepository;
import com.fatih.fegp.citizen.domain.CitizenStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CitizenServiceTest {
    @Mock
    private CitizenRepository citizenRepository;

    private CitizenService citizenService;

    @BeforeEach
    public void setUp() {
        citizenService = new CitizenService(citizenRepository);
    }

    @Test
    public void givenCitizenIdNotExists_whenSave_thenSavesCitizen() {
        long citizenId = 10000000146L;
        Citizen citizen = new Citizen(citizenId, "Fatih", CitizenStatus.ACTIVE);

        when(citizenRepository.existsByCitizenId(citizenId)).thenReturn(false);
        when(citizenRepository.save(citizen)).thenReturn(citizen);

        Citizen result = citizenRepository.save(citizen);

        assertEquals(citizen, result);
        verify(citizenRepository).save(citizen);
    }

    @Test
    public void givenCitizenIdExists_whenSave_thenThrowsCitizenAlreadyExists() {
        long citizenId = 10000000146L;
        Citizen citizen = new Citizen(citizenId, "Fatih", CitizenStatus.ACTIVE);

        when(citizenRepository.existsByCitizenId(citizenId)).thenReturn(true);

        try {
            citizenService.save(citizen);
            fail("Expected CitizenAlreadyExists to be thrown.");
        } catch (CitizenErrors.CitizenAlreadyExists exception) {
            assertEquals(CitizenErrors.CITIZEN_ALREADY_EXISTS, exception.getCode());
        }
    }
}

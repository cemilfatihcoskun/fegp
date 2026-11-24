package com.fatih.fegp.citizen.web;

import com.fatih.fegp.citizen.application.CitizenService;
import com.fatih.fegp.citizen.domain.Citizen;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/citizens")
public class CitizenController {
    private final CitizenService citizenService;

    public CitizenController(CitizenService citizenService) {
        this.citizenService = citizenService;
    }

    @PostMapping
    public ResponseEntity<CitizenResponse> save(@RequestBody RegisterCitizenRequest request) {
        if (request == null) {
            return ResponseEntity.badRequest().build();
        }
        Citizen savedCitizen = citizenService.save();
        return ResponseEntity.ok(CitizenResponse.from(savedCitizen));
    }

    @GetMapping("/{citizenId}")
    public ResponseEntity<CitizenResponse> findByCitizenId(@PathVariable long citizenId) {
        Citizen citizen = citizenService.findByCitizenId(citizenId);
        return ResponseEntity.ok(CitizenResponse.from(citizen));
    }

    @GetMapping("/suspend/{citizenId}")
    public ResponseEntity<CitizenResponse> suspendByCitizenId(@PathVariable long citizenId) {
        Citizen citizen = citizenService.suspend(citizenId);
        return ResponseEntity.ok(CitizenResponse.from(citizen));
    }

    @GetMapping("/activate/{citizenId}")
    public ResponseEntity<CitizenResponse> activateByCitizenId(@PathVariable long citizenId) {
        Citizen citizen = citizenService.activate(citizenId);
        return ResponseEntity.ok(CitizenResponse.from(citizen));
    }
}

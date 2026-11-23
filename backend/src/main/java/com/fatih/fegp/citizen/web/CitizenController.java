package com.fatih.fegp.citizen.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatih.fegp.citizen.application.CitizenService;
import com.fatih.fegp.citizen.domain.Citizen;

@RestController
@RequestMapping("/api/citizens")
public class CitizenController {
    private final CitizenService service;

    public CitizenController(CitizenService service) {
	this.service = service;
    }

    @PostMapping
    public ResponseEntity<CitizenResponse> register(@RequestBody RegisterCitizenRequest request) {
	if (request == null) {
	    return ResponseEntity.badRequest().build();
	}
        Citizen citizen = service.registerCitizen(request.nationalId(), request.name());
        if (citizen == null) {
            return ResponseEntity.internalServerError().build();
        }
	return ResponseEntity.ok(CitizenResponse.from(citizen));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitizenResponse> findById(@PathVariable long id) {
	Citizen citizen = service.findById(id);
	if (citizen == null) {
	    return ResponseEntity.notFound().build();
	}
	return ResponseEntity.ok(CitizenResponse.from(citizen));
    }

    @GetMapping("/nationalId/{nationalId}")
    public ResponseEntity<CitizenResponse> findByNationalId(@PathVariable long nationalId) {
	Citizen citizen = service.findByNationalId(nationalId);
	if (citizen == null) {
	    return ResponseEntity.notFound().build();
	}
	return ResponseEntity.ok(CitizenResponse.from(citizen));
    }

    @GetMapping("/suspend/{id}")
    public ResponseEntity<CitizenResponse> suspend(@PathVariable long id) {
	Citizen citizen = service.suspend(id);
	if (citizen == null) {
	    return ResponseEntity.notFound().build();
	}
	return ResponseEntity.ok(CitizenResponse.from(citizen));
    }

    @GetMapping("/activate/{id}")
    public ResponseEntity<CitizenResponse> activatae(@PathVariable long id) {
	Citizen citizen = service.activate(id);
	if (citizen == null) {
	    return ResponseEntity.notFound().build();
	}
	return ResponseEntity.ok(CitizenResponse.from(citizen));
    }
}

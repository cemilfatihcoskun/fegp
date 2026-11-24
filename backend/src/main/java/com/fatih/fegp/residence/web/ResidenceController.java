package com.fatih.fegp.residence.web;

import com.fatih.fegp.residence.application.ResidenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/residences")
public class ResidenceController {
    private final ResidenceService residenceService;

    public ResidenceController(ResidenceService residenceService) {
        this.residenceService = residenceService;
    }

    @GetMapping("/{nationalId}")
    public ResponseEntity<ResidenceResponse> getResidence(@PathVariable long nationalId) {
        ResidenceResponse response = residenceService.findByResidenceId(nationalId);
        return new ResponseEntity<ResidenceResponse>(response, HttpStatus.OK);
    }
}

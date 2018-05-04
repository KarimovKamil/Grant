package ru.itis.grant.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.grant.dto.ValidateDto;
import ru.itis.grant.dto.request.AuthDto;
import ru.itis.grant.service.interfaces.ExpertService;

@RestController
@RequestMapping("/experts")
public class ExpertController {

    @Autowired
    ExpertService expertService;

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody AuthDto authDto) {
        String token = expertService.login(authDto);
        return ResponseEntity.ok(token);
    }

    @PostMapping(value = "events/{eventId}/patterns/{patternId}/validate")
    public ResponseEntity<Boolean> validatePatternOK(
            @PathVariable(value = "eventId") long eventId,
            @PathVariable(value = "patternId") long patternId,
            @RequestBody ValidateDto validateDto) {
        boolean success = expertService.validatePattern(eventId, patternId, validateDto);
        return ResponseEntity.ok(success);
    }
}

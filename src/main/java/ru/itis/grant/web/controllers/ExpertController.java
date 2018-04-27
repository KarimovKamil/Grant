package ru.itis.grant.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.grant.dto.AuthDto;
import ru.itis.grant.dto.EventDto;
import ru.itis.grant.dto.PatternDto;
import ru.itis.grant.dto.ValidateDto;
import ru.itis.grant.service.interfaces.ExpertService;

import java.util.List;

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

    @GetMapping(value = "events")
    public ResponseEntity<List<EventDto>> events() {
        List<EventDto> eventDto = expertService.getEvents();
        return ResponseEntity.ok(eventDto);
    }

    @GetMapping(value = "events/active")
    public ResponseEntity<List<EventDto>> activeEvents() {
        List<EventDto> eventDto = expertService.getActiveEvents();
        return ResponseEntity.ok(eventDto);
    }

    @GetMapping(value = "events/{id}")
    public ResponseEntity<EventDto> eventById(@PathVariable(value = "id") long id) {
        EventDto eventDto = expertService.getEvent(id);
        return ResponseEntity.ok(eventDto);
    }

    @GetMapping(value = "events/{id}/patterns")
    public ResponseEntity<List<PatternDto>> eventPatternsById(@PathVariable(value = "id") long id) {
        List<PatternDto> patternDto = expertService.getEventsPatterns(id);
        return ResponseEntity.ok(patternDto);
    }

    @GetMapping(value = "events/{eventId}/patterns/{patternId}")
    public ResponseEntity<PatternDto> eventPatternById(
            @PathVariable(value = "eventId") long eventId,
            @PathVariable(value = "patternId") long patternId) {
        PatternDto patternDto = expertService.getPattern(eventId, patternId);
        return ResponseEntity.ok(patternDto);
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

package ru.itis.grant.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.grant.dto.AuthDto;
import ru.itis.grant.dto.EventDto;
import ru.itis.grant.dto.PatternDto;
import ru.itis.grant.service.interfaces.CreatorService;

import java.util.List;

@RestController
@RequestMapping("/creators")
public class CreatorController {

    @Autowired
    CreatorService creatorService;

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody AuthDto authDto) {
        String token = creatorService.login(authDto);
        return ResponseEntity.ok(token);
    }

    @GetMapping(value = "events")
    public ResponseEntity<List<EventDto>> events() {
        List<EventDto> eventDto = creatorService.getEvents();
        return ResponseEntity.ok(eventDto);
    }

    @GetMapping(value = "events/active")
    public ResponseEntity<List<EventDto>> activeEvents() {
        List<EventDto> eventDto = creatorService.getActiveEvents();
        return ResponseEntity.ok(eventDto);
    }

    @GetMapping(value = "events/{id}")
    public ResponseEntity<EventDto> eventById(@PathVariable(value = "id") long id) {
        EventDto eventDto = creatorService.getEvent(id);
        return ResponseEntity.ok(eventDto);
    }

    @PostMapping(value = "events/{eventId}/createPattern")
    public ResponseEntity<PatternDto> createPattern(
            @PathVariable(value = "eventId") long eventId,
            @RequestBody PatternDto patternDto) {
        PatternDto createdPattern = creatorService.createPattern(eventId, patternDto);
        return ResponseEntity.ok(createdPattern);
    }

    @GetMapping(value = "/my/patterns")
    public ResponseEntity<List<PatternDto>> creatorPatterns(
            @RequestParam(value = "Auth-Token") String token) {
        List<PatternDto> patterns = creatorService.getPatterns(token);
        return ResponseEntity.ok(patterns);
    }

    @GetMapping(value = "/my/patterns/{id}")
    public ResponseEntity<PatternDto> creatorPattern(
            @RequestParam(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id) {
        PatternDto pattern = creatorService.getPattern(token, id);
        return ResponseEntity.ok(pattern);
    }


    @PostMapping(value = "/my/patterns/{id}/update")
    public ResponseEntity<PatternDto> updatePattern(
            @RequestParam(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id,
            @RequestBody PatternDto patternDto) {
        patternDto.setId(id);
        PatternDto updatedBid = creatorService.updatePattern(token, patternDto);
        return ResponseEntity.ok(updatedBid);
    }

    @PostMapping(value = "/my/patterns/{id}/delete")
    public ResponseEntity<Boolean> deletePattern(
            @RequestParam(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id) {
        boolean success = creatorService.deletePattern(token, id);
        return ResponseEntity.ok(success);
    }

}

package ru.itis.grant.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.grant.dto.request.AuthDto;
import ru.itis.grant.dto.request.RequestEventDto;
import ru.itis.grant.dto.request.RequestPatternDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.dto.response.ResponsePatternDto;
import ru.itis.grant.service.interfaces.OrganizerService;

import java.util.List;

@RestController
@RequestMapping("/organizers")
public class OrganizerController {

    @Autowired
    OrganizerService organizerService;

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody AuthDto authDto) {
        String token = organizerService.login(authDto);
        return ResponseEntity.ok(token);
    }

    @PostMapping(value = "/events/pattern/create")
    public ResponseEntity<ResponsePatternDto> createPattern(
            @RequestBody RequestPatternDto patternDto) {
        ResponsePatternDto createdPattern = organizerService.createPattern(patternDto);
        return ResponseEntity.ok(createdPattern);
    }

    @GetMapping(value = "/my/events")
    public ResponseEntity<List<ResponseEventDto>> organizerEvents(
            @RequestParam(value = "Auth-Token") String token) {
        List<ResponseEventDto> patterns = organizerService.getUserEvents(token);
        return ResponseEntity.ok(patterns);
    }

    @GetMapping(value = "/my/patterns/{id}")
    public ResponseEntity<ResponsePatternDto> organizerPattern(
            @RequestParam(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id) {
        ResponsePatternDto pattern = organizerService.getPattern(token, id);
        return ResponseEntity.ok(pattern);
    }

    @PostMapping(value = "/my/patterns/{id}/update")
    public ResponseEntity<ResponsePatternDto> updatePattern(
            @RequestParam(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id,
            @RequestBody RequestPatternDto patternDto) {
//        patternDto.setId(id);
        ResponsePatternDto updatedBid = organizerService.updatePattern(token, patternDto);
        return ResponseEntity.ok(updatedBid);
    }

    @PostMapping(value = "/my/patterns/{id}/delete")
    public ResponseEntity<Boolean> deletePattern(
            @RequestParam(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id) {
        boolean success = organizerService.deletePattern(token, id);
        return ResponseEntity.ok(success);
    }

    @PostMapping(value = "/my/events/{id}/update")
    public ResponseEntity<ResponsePatternDto> updateEvent(
            @RequestParam(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id,
            @RequestBody RequestEventDto requestEventDto) {
        return null;
    }

    @PostMapping(value = "/my/events/{id}/delete")
    public ResponseEntity<Boolean> deleteEvent(
            @RequestParam(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id) {
        return null;
    }
}

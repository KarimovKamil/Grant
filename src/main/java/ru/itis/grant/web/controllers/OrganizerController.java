package ru.itis.grant.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.grant.dto.request.RequestEventDto;
import ru.itis.grant.dto.request.RequestPatternDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.dto.response.ResponsePatternDto;
import ru.itis.grant.dto.response.ResponseUserDto;
import ru.itis.grant.service.interfaces.OrganizerService;

import java.util.List;

@RestController
@RequestMapping("/organizers")
public class OrganizerController {

    @Autowired
    OrganizerService organizerService;

    @PostMapping(value = "/events/create")
    public ResponseEntity<ResponseEventDto> createEvent(
            @RequestBody RequestEventDto requestEventDto,
            @RequestHeader(value = "Auth-Token") String token) {
        ResponseEventDto eventDto = organizerService.createEvent(requestEventDto, token);
        return ResponseEntity.ok(eventDto);
    }

    @PostMapping(value = "/events/pattern/create")
    public ResponseEntity<ResponsePatternDto> createPattern(
            @RequestBody RequestPatternDto patternDto,
            @RequestHeader(value = "Auth-Token") String token) {
        ResponsePatternDto createdPattern = organizerService.createPattern(patternDto, token);
        return ResponseEntity.ok(createdPattern);
    }

    @GetMapping(value = "/events")
    public ResponseEntity<List<ResponseEventDto>> organizerEvents(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestParam(value = "from") long from,
            @RequestParam(value = "count") long count) {
        List<ResponseEventDto> patterns = organizerService.getOrganizerEvents(token, from, count);
        return ResponseEntity.ok(patterns);
    }

    @PostMapping(value = "/events/{id}/update")
    public ResponseEntity<ResponseEventDto> updateEvent(
            @RequestHeader(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id,
            @RequestBody RequestEventDto requestEventDto) {
        ResponseEventDto responseEventDto = organizerService.updateEvent(requestEventDto, id, token);
        return ResponseEntity.ok(responseEventDto);
    }

    @PostMapping(value = "/events/{id}/delete")
    public ResponseEntity<String> deleteEvent(
            @RequestHeader(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id) {
        organizerService.deleteEvent(id, token);
        return ResponseEntity.ok("Event deleted");
    }

    @PostMapping(value = "/events/{eventId}/experts/add")
    public ResponseEntity<String> addExpertToEvent(
            @PathVariable(value = "eventId") long eventId,
            @RequestParam long expertId,
            @RequestHeader(value = "Auth-Token") String token) {
        organizerService.addExpertToEvent(expertId, eventId, token);
        return ResponseEntity.ok("Expert added");
    }

    @PostMapping(value = "/events/{eventId}/experts/{expertId}/delete")
    public ResponseEntity<String> deleteExpertFromEvent(
            @PathVariable(value = "eventId") long eventId,
            @PathVariable(value = "expertId") long expertId,
            @RequestHeader(value = "Auth-Token") String token) {
        organizerService.deleteExpertFromEvent(expertId, eventId, token);
        return ResponseEntity.ok("Expert deleted from event");
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<ResponseUserDto>> allUsers(
            @RequestParam(value = "from") int from,
            @RequestParam(value = "count") int count) {
        List<ResponseUserDto> users = organizerService.getUsers(from, count);
        return ResponseEntity.ok(users);
    }
}

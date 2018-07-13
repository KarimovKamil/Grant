package ru.itis.grant.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.grant.dto.request.RequestEventDto;
import ru.itis.grant.dto.request.RequestPatternDto;
import ru.itis.grant.dto.response.ResponseBidDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.dto.response.ResponsePatternDto;
import ru.itis.grant.dto.response.ResponseUserDto;
import ru.itis.grant.service.interfaces.BidService;
import ru.itis.grant.service.interfaces.OrganizerService;

import java.util.List;

@RestController
@RequestMapping("/organizers")
public class OrganizerController {

    @Autowired
    OrganizerService organizerService;
    @Autowired
    BidService bidService;

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public ResponseEntity<ResponseEventDto> createEvent(
            @RequestBody RequestEventDto requestEventDto,
            @RequestHeader(value = "Auth-Token") String token) {
        ResponseEventDto eventDto = organizerService.createEvent(requestEventDto, token);
        return ResponseEntity.ok(eventDto);
    }

    @RequestMapping(value = "/events/pattern", method = RequestMethod.POST)
    public ResponseEntity<ResponsePatternDto> createPattern(
            @RequestBody RequestPatternDto patternDto,
            @RequestHeader(value = "Auth-Token") String token) {
        ResponsePatternDto createdPattern = organizerService.createPattern(patternDto, token);
        return ResponseEntity.ok(createdPattern);
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public ResponseEntity<List<ResponseEventDto>> organizerEvents(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestParam(value = "from") long from,
            @RequestParam(value = "count") long count) {
        List<ResponseEventDto> patterns = organizerService.getOrganizerEvents(token, from, count);
        return ResponseEntity.ok(patterns);
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEventDto> updateEvent(
            @RequestHeader(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id,
            @RequestBody RequestEventDto requestEventDto) {
        ResponseEventDto responseEventDto = organizerService.updateEvent(requestEventDto, id, token);
        return ResponseEntity.ok(responseEventDto);
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteEvent(
            @RequestHeader(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id) {
        organizerService.deleteEvent(id, token);
        return ResponseEntity.ok("Event deleted");
    }

    @RequestMapping(value = "/events/{eventId}/experts/{expertId}", method = RequestMethod.POST)
    public ResponseEntity<String> addExpertToEvent(
            @PathVariable(value = "eventId") long eventId,
            @PathVariable(value = "expertId") long expertId,
            @RequestHeader(value = "Auth-Token") String token) {
        organizerService.addExpertToEvent(expertId, eventId, token);
        return ResponseEntity.ok("Expert added");
    }

    @RequestMapping(value = "/events/{eventId}/experts/{expertId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteExpertFromEvent(
            @PathVariable(value = "eventId") long eventId,
            @PathVariable(value = "expertId") long expertId,
            @RequestHeader(value = "Auth-Token") String token) {
        organizerService.deleteExpertFromEvent(expertId, eventId, token);
        return ResponseEntity.ok("Expert deleted from event");
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<ResponseUserDto>> allUsers(
            @RequestParam(value = "from") int from,
            @RequestParam(value = "count") int count) {
        List<ResponseUserDto> users = organizerService.getUsers(from, count);
        return ResponseEntity.ok(users);
    }

    @RequestMapping(value = "/bids/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getBidString(
            @RequestHeader(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id) {
        String bidString = bidService.getBidInString(id);
        return ResponseEntity.ok(bidString);
    }
}

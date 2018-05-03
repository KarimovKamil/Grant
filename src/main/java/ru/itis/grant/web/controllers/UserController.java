package ru.itis.grant.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.grant.dto.AuthDto;
import ru.itis.grant.dto.BidDto;
import ru.itis.grant.dto.EventDto;
import ru.itis.grant.dto.PatternDto;
import ru.itis.grant.service.interfaces.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody AuthDto authDto) {
        String token = userService.login(authDto);
        return ResponseEntity.ok(token);
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<String> registration(@RequestBody AuthDto authDto) {
        String token = userService.register(authDto);
        return ResponseEntity.ok(token);
    }

    @GetMapping(value = "events")
    public ResponseEntity<List<EventDto>> events() {
        List<EventDto> eventDto = userService.getEvents();
        return ResponseEntity.ok(eventDto);
    }

    @GetMapping(value = "events/active")
    public ResponseEntity<List<EventDto>> activeEvents() {
        List<EventDto> eventDto = userService.getActiveEvents();
        return ResponseEntity.ok(eventDto);
    }

    @GetMapping(value = "events/{id}")
    public ResponseEntity<EventDto> eventById(@PathVariable(value = "id") long id) {
        EventDto eventDto = userService.getEvent(id);
        return ResponseEntity.ok(eventDto);
    }

    @GetMapping(value = "events/{id}/patterns")
    public ResponseEntity<List<PatternDto>> eventPatternsById(@PathVariable(value = "id") long id) {
        List<PatternDto> patternDto = userService.getEventsPatterns(id);
        return ResponseEntity.ok(patternDto);
    }

    @GetMapping(value = "events/{eventId}/patterns/{patternId}")
    public ResponseEntity<PatternDto> eventPatternById(
            @PathVariable(value = "eventId") long eventId,
            @PathVariable(value = "patternId") long patternId) {
        PatternDto patternDto = userService.getEventPattern(eventId, patternId);
        return ResponseEntity.ok(patternDto);
    }

    @PostMapping(value = "events/{eventId}/patterns/{patternId}/create")
    public ResponseEntity<BidDto> createBid(
            @PathVariable(value = "eventId") long eventId,
            @PathVariable(value = "patternId") long patternId,
            @RequestBody BidDto bidDto) {
        BidDto createdBid = userService.createBid(eventId, patternId, bidDto);
        return ResponseEntity.ok(createdBid);
    }

    @GetMapping(value = "/my/bids")
    public ResponseEntity<List<BidDto>> userBids(
            @RequestParam(value = "Auth-Token") String token) {
        List<BidDto> bids = userService.getUserBids(token);
        return ResponseEntity.ok(bids);
    }


    @GetMapping(value = "/my/bids/{id}")
    public ResponseEntity<BidDto> userBid(
            @RequestParam(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id) {
        BidDto bid = userService.getBid(token, id);
        return ResponseEntity.ok(bid);
    }

    @PostMapping(value = "/my/bids/{id}/update")
    public ResponseEntity<BidDto> updateBid(
            @RequestParam(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id,
            @RequestBody BidDto bidDto) {
        bidDto.setId(id);
        BidDto updatedBid = userService.updateBid(token, bidDto);
        return ResponseEntity.ok(updatedBid);
    }

    @PostMapping(value = "/my/bids/{id}/delete")
    public ResponseEntity<Boolean> deleteBid(
            @RequestParam(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id) {
        boolean success = userService.deleteBid(token, id);
        return ResponseEntity.ok(success);
    }
}

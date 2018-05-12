package ru.itis.grant.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.grant.dto.request.AuthDto;
import ru.itis.grant.dto.request.RequestBidDto;
import ru.itis.grant.dto.request.RequestUserDto;
import ru.itis.grant.dto.response.ResponseBidDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.dto.response.ResponsePatternDto;
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
    public ResponseEntity<String> registration(@RequestBody RequestUserDto requestUserDto) {
        String token = userService.register(requestUserDto);
        return ResponseEntity.ok(token);
    }

    @GetMapping(value = "/events")
    public ResponseEntity<List<ResponseEventDto>> events() {
        List<ResponseEventDto> requestEventDto = userService.getEvents();
        return ResponseEntity.ok(requestEventDto);
    }

    @GetMapping(value = "/events/active")
    public ResponseEntity<List<ResponseEventDto>> activeEvents() {
        List<ResponseEventDto> requestEventDto = userService.getActiveEvents();
        return ResponseEntity.ok(requestEventDto);
    }

    @GetMapping(value = "/events/activeWithPattern")
    public ResponseEntity<List<ResponseEventDto>> activeEventsWithPattern() {
        List<ResponseEventDto> requestEventDto = userService.getActiveEventsWithPattern();
        return ResponseEntity.ok(requestEventDto);
    }

    @GetMapping(value = "/events/{id}")
    public ResponseEntity<ResponseEventDto> eventById(@PathVariable(value = "id") long id) {
        ResponseEventDto requestEventDto = userService.getEvent(id);
        return ResponseEntity.ok(requestEventDto);
    }

    @GetMapping(value = "/events/{eventId}/pattern")
    public ResponseEntity<ResponsePatternDto> eventPattern(
            @PathVariable(value = "eventId") long eventId) {
        ResponsePatternDto patternDto = userService.getEventPattern(eventId);
        return ResponseEntity.ok(patternDto);
    }

    @PostMapping(value = "/my/bids/add")
    public ResponseEntity<ResponseBidDto> createBid(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody RequestBidDto requestBidDto) {
        ResponseBidDto createdBid = userService.createBid(token, requestBidDto);
        return ResponseEntity.ok(createdBid);
    }

    @GetMapping(value = "/my/bids")
    public ResponseEntity<List<ResponseBidDto>> userBids(
            @RequestHeader(value = "Auth-Token") String token) {
        List<ResponseBidDto> bids = userService.getUserBids(token);
        return ResponseEntity.ok(bids);
    }

    @GetMapping(value = "/my/bids/{id}")
    public ResponseEntity<ResponseBidDto> userBid(
            @RequestHeader(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id) {
        ResponseBidDto bid = userService.getBid(token, id);
        return ResponseEntity.ok(bid);
    }

    @PostMapping(value = "/my/bids/{id}/update")
    public ResponseEntity<ResponseBidDto> updateBid(
            @RequestHeader(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id,
            @RequestBody RequestBidDto requestBidDto) {
        ResponseBidDto updatedBid = userService.updateBid(id, token, requestBidDto);
        return ResponseEntity.ok(updatedBid);
    }

    @PostMapping(value = "/my/bids/{id}/delete")
    public ResponseEntity<Boolean> deleteBid(
            @RequestHeader(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id) {
        boolean success = userService.deleteBid(token, id);
        return ResponseEntity.ok(success);
    }
}

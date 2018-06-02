package ru.itis.grant.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.grant.dto.request.AuthDto;
import ru.itis.grant.dto.request.RequestBidDto;
import ru.itis.grant.dto.request.RequestUserDto;
import ru.itis.grant.dto.request.UserUpdateDto;
import ru.itis.grant.dto.response.ResponseBidDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.dto.response.ResponsePatternDto;
import ru.itis.grant.dto.response.ResponseUserDto;
import ru.itis.grant.service.interfaces.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(
            @RequestBody AuthDto authDto) {
        String token = userService.login(authDto);
        return ResponseEntity.ok(token);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<String> registration(
            @RequestBody RequestUserDto requestUserDto) {
        String token = userService.register(requestUserDto);
        return ResponseEntity.ok(token);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ResponseEntity<ResponseUserDto> profile(
            @RequestHeader(value = "Auth-Token") String token) {
        ResponseUserDto responseUserDto = userService.userInfo(token);
        return ResponseEntity.ok(responseUserDto);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.PUT)
    public ResponseEntity<ResponseUserDto> updateInfo(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody UserUpdateDto userUpdateDto) {
        ResponseUserDto responseUserDto = userService.updateUserInfo(token, userUpdateDto);
        return ResponseEntity.ok(responseUserDto);
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public ResponseEntity<List<ResponseEventDto>> events(
            @RequestParam int from,
            @RequestParam int count) {
        List<ResponseEventDto> requestEventDto = userService.getEvents(from, count);
        return ResponseEntity.ok(requestEventDto);
    }

    @RequestMapping(value = "/events/active", method = RequestMethod.GET)
    public ResponseEntity<List<ResponseEventDto>> activeEvents(
            @RequestParam int from,
            @RequestParam int count) {
        List<ResponseEventDto> requestEventDto = userService.getActiveEvents(from, count);
        return ResponseEntity.ok(requestEventDto);
    }

    @RequestMapping(value = "/events/activeWithPattern", method = RequestMethod.GET)
    public ResponseEntity<List<ResponseEventDto>> activeEventsWithPattern(
            @RequestParam int from,
            @RequestParam int count) {
        List<ResponseEventDto> requestEventDto = userService.getActiveEventsWithPattern(from, count);
        return ResponseEntity.ok(requestEventDto);
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEventDto> eventById(@PathVariable(value = "id") long id) {
        ResponseEventDto requestEventDto = userService.getEvent(id);
        return ResponseEntity.ok(requestEventDto);
    }

    @RequestMapping(value = "/events/{eventId}/pattern", method = RequestMethod.GET)
    public ResponseEntity<ResponsePatternDto> eventPattern(
            @PathVariable(value = "eventId") long eventId) {
        ResponsePatternDto patternDto = userService.getEventPattern(eventId);
        return ResponseEntity.ok(patternDto);
    }

    @RequestMapping(value = "/bids", method = RequestMethod.POST)
    public ResponseEntity<ResponseBidDto> createBid(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody RequestBidDto requestBidDto) {
        ResponseBidDto createdBid = userService.createBid(token, requestBidDto);
        return ResponseEntity.ok(createdBid);
    }

    @RequestMapping(value = "/bids", method = RequestMethod.GET)
    public ResponseEntity<List<ResponseBidDto>> userBids(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestParam int from,
            @RequestParam int count) {
        List<ResponseBidDto> bids = userService.getUserBids(token, from, count);
        return ResponseEntity.ok(bids);
    }

    @RequestMapping(value = "/bids/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseBidDto> userBid(
            @RequestHeader(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id) {
        ResponseBidDto bid = userService.getBid(token, id);
        return ResponseEntity.ok(bid);
    }

    @RequestMapping(value = "/bids/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseBidDto> updateBid(
            @RequestHeader(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id,
            @RequestBody RequestBidDto requestBidDto) {
        ResponseBidDto updatedBid = userService.updateBid(id, token, requestBidDto);
        return ResponseEntity.ok(updatedBid);
    }

    @RequestMapping(value = "/bids/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteBid(
            @RequestHeader(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id) {
        boolean success = userService.deleteBid(token, id);
        return ResponseEntity.ok(success);
    }
}

package ru.itis.grant.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.grant.dto.TokenDto;
import ru.itis.grant.dto.request.AuthDto;
import ru.itis.grant.dto.request.RequestApplicationDto;
import ru.itis.grant.dto.request.RequestUserDto;
import ru.itis.grant.dto.request.UserUpdateDto;
import ru.itis.grant.dto.response.ResponseApplicationDto;
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
    public ResponseEntity<TokenDto> login(
            @RequestBody AuthDto authDto) {
        TokenDto token = userService.login(authDto);
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

    @RequestMapping(value = "/events/{eventId}/pattern/application", method = RequestMethod.GET)
    public ResponseEntity<ResponseApplicationDto> eventPatternApplication(
            @RequestHeader(value = "Auth-Token") String token,
            @PathVariable(value = "eventId") long eventId) {
        ResponseApplicationDto applicationDto = userService.getApplicationByEvent(token, eventId);
        return ResponseEntity.ok(applicationDto);
    }

    @RequestMapping(value = "/applications", method = RequestMethod.POST)
    public ResponseEntity<ResponseApplicationDto> createApplication(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestBody RequestApplicationDto requestApplicationDto) {
        ResponseApplicationDto createdApplication = userService.createApplication(token, requestApplicationDto);
        return ResponseEntity.ok(createdApplication);
    }

    @RequestMapping(value = "/applications", method = RequestMethod.GET)
    public ResponseEntity<List<ResponseApplicationDto>> userApplications(
            @RequestHeader(value = "Auth-Token") String token,
            @RequestParam int from,
            @RequestParam int count) {
        List<ResponseApplicationDto> applications = userService.getUserApplications(token, from, count);
        return ResponseEntity.ok(applications);
    }

    @RequestMapping(value = "/applications/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseApplicationDto> userApplication(
            @RequestHeader(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id) {
        ResponseApplicationDto application = userService.getApplication(token, id);
        return ResponseEntity.ok(application);
    }

    @RequestMapping(value = "/applications/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseApplicationDto> updateApplication(
            @RequestHeader(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id,
            @RequestBody RequestApplicationDto requestApplicationDto) {
        ResponseApplicationDto updatedApplication = userService.updateApplication(id, token, requestApplicationDto);
        return ResponseEntity.ok(updatedApplication);
    }

    @RequestMapping(value = "/applications/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteApplication(
            @RequestHeader(value = "Auth-Token") String token,
            @PathVariable(value = "id") long id) {
        boolean success = userService.deleteApplication(token, id);
        return ResponseEntity.ok(success);
    }
}

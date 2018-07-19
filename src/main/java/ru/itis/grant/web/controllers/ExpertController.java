package ru.itis.grant.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.grant.dto.ValidateDto;
import ru.itis.grant.dto.response.ResponseApplicationDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.service.interfaces.ExpertService;

import java.util.List;

@RestController
@RequestMapping("/experts")
public class ExpertController {

    @Autowired
    ExpertService expertService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public ResponseEntity<List<ResponseEventDto>> getExpertEvents(
            @RequestHeader("Auth-token") String token,
            @RequestParam(value = "from") int from,
            @RequestParam(value = "count") int count) {
        List<ResponseEventDto> responseEventDtos = expertService.getExpertEvents(token, from, count);
        return ResponseEntity.ok(responseEventDtos);
    }

    @RequestMapping(value = "/applications", method = RequestMethod.GET)
    public ResponseEntity<List<ResponseApplicationDto>> getExpertApplications(
            @RequestHeader("Auth-token") String token,
            @RequestParam(value = "from") int from,
            @RequestParam(value = "count") int count) {
        List<ResponseApplicationDto> responseApplicationDtos = expertService.getExpertApplications(token, from, count);
        return ResponseEntity.ok(responseApplicationDtos);
    }

    @RequestMapping(value = "/applications/{applicationId}", method = RequestMethod.GET)
    public ResponseEntity<ResponseApplicationDto> getExpertApplication(
            @RequestHeader("Auth-token") String token,
            @PathVariable("applicationId") long applicationId) {
        ResponseApplicationDto responseApplicationDto = expertService.getExpertApplication(token, applicationId);
        return ResponseEntity.ok(responseApplicationDto);
    }

    @RequestMapping(value = "/events/{eventId}/applications", method = RequestMethod.GET)
    public ResponseEntity<List<ResponseApplicationDto>> getExpertEventApplications(
            @RequestHeader("Auth-token") String token,
            @PathVariable("eventId") long eventId,
            @RequestParam(value = "from") int from,
            @RequestParam(value = "count") int count) {
        List<ResponseApplicationDto> responseApplicationDtos = expertService
                .getExpertEventApplications(token, eventId, from, count);
        return ResponseEntity.ok(responseApplicationDtos);
    }

    @RequestMapping(value = "/applications/{applicationId}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseApplicationDto> validate(
            @RequestHeader("Auth-token") String token,
            @PathVariable("applicationId") long applicationId,
            @RequestBody ValidateDto validateDto) {
        ResponseApplicationDto responseApplicationDto = expertService.validate(token, applicationId, validateDto);
        return ResponseEntity.ok(responseApplicationDto);
    }
}

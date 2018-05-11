package ru.itis.grant.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.grant.dto.ValidateDto;
import ru.itis.grant.dto.response.ResponseBidDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.service.interfaces.ExpertService;

import java.util.List;

@RestController
@RequestMapping("/experts")
public class ExpertController {

    @Autowired
    ExpertService expertService;

    @GetMapping(value = "/events")
    public ResponseEntity<List<ResponseEventDto>> getExpertEvents(@RequestHeader("Auth-token") String token) {
        List<ResponseEventDto> responseEventDtos = expertService.getExpertEvents(token);
        return ResponseEntity.ok(responseEventDtos);
    }

    @GetMapping(value = "/bids")
    public ResponseEntity<List<ResponseBidDto>> getExpertBids(@RequestHeader("Auth-token") String token) {
        List<ResponseBidDto> responseBidDtos = expertService.getExpertBids(token);
        return ResponseEntity.ok(responseBidDtos);
    }

    @GetMapping(value = "/bids/{bidId}")
    public ResponseEntity<ResponseBidDto> getExpertBids(@RequestHeader("Auth-token") String token,
                                                        @PathVariable("bidId") long bidId) {
        ResponseBidDto responseBidDto = expertService.getExpertBid(token, bidId);
        return ResponseEntity.ok(responseBidDto);
    }

    @GetMapping(value = "/events/{eventId}/bids")
    public ResponseEntity<List<ResponseBidDto>> getExpertEventBids(@RequestHeader("Auth-token") String token,
                                                                   @PathVariable("eventId") long eventId) {
        List<ResponseBidDto> responseBidDtos = expertService.getExpertEventBids(token, eventId);
        return ResponseEntity.ok(responseBidDtos);
    }

    @PostMapping(value = "/bids/{bidId}/validate")
    public ResponseEntity<ResponseBidDto> validate(@RequestHeader("Auth-token") String token,
                                                   @PathVariable("bidId") long bidId,
                                                   @RequestBody ValidateDto validateDto) {
        ResponseBidDto responseBidDto = expertService.validate(token, bidId, validateDto);
        return ResponseEntity.ok(responseBidDto);
    }
}

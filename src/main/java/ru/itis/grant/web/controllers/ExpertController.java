package ru.itis.grant.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.grant.dto.ValidateDto;
import ru.itis.grant.dto.response.ResponseBanDto;
import ru.itis.grant.dto.response.ResponseBidDto;
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
            @RequestParam(value = "from") long from,
            @RequestParam(value = "count") long count) {
        List<ResponseEventDto> responseEventDtos = expertService.getExpertEvents(token, from, count);
        return ResponseEntity.ok(responseEventDtos);
    }

    @RequestMapping(value = "/bids", method = RequestMethod.GET)
    public ResponseEntity<List<ResponseBidDto>> getExpertBids(
            @RequestHeader("Auth-token") String token,
            @RequestParam(value = "from") long from,
            @RequestParam(value = "count") long count) {
        List<ResponseBidDto> responseBidDtos = expertService.getExpertBids(token, from, count);
        return ResponseEntity.ok(responseBidDtos);
    }

    @RequestMapping(value = "/bids/{bidId}", method = RequestMethod.GET)
    public ResponseEntity<ResponseBidDto> getExpertBid(
            @RequestHeader("Auth-token") String token,
            @PathVariable("bidId") long bidId) {
        ResponseBidDto responseBidDto = expertService.getExpertBid(token, bidId);
        return ResponseEntity.ok(responseBidDto);
    }

    @RequestMapping(value = "/events/{eventId}/bids", method = RequestMethod.GET)
    public ResponseEntity<List<ResponseBidDto>> getExpertEventBids(
            @RequestHeader("Auth-token") String token,
            @PathVariable("eventId") long eventId,
            @RequestParam(value = "from") long from,
            @RequestParam(value = "count") long count) {
        List<ResponseBidDto> responseBidDtos = expertService.getExpertEventBids(token, eventId, from, count);
        return ResponseEntity.ok(responseBidDtos);
    }

    @RequestMapping(value = "/bids/{bidId}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseBidDto> validate(
            @RequestHeader("Auth-token") String token,
            @PathVariable("bidId") long bidId,
            @RequestBody ValidateDto validateDto) {
        ResponseBidDto responseBidDto = expertService.validate(token, bidId, validateDto);
        return ResponseEntity.ok(responseBidDto);
    }
}

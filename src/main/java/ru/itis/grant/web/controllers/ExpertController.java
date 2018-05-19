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

    @GetMapping(value = "/events")
    public ResponseEntity<List<ResponseEventDto>> getExpertEvents(@RequestHeader("Auth-token") String token,
                                                                  @RequestParam(value = "from") long from,
                                                                  @RequestParam(value = "count") long count) {
        List<ResponseEventDto> responseEventDtos = expertService.getExpertEvents(token, from, count);
        return ResponseEntity.ok(responseEventDtos);
    }

    @GetMapping(value = "/bids")
    public ResponseEntity<List<ResponseBidDto>> getExpertBids(@RequestHeader("Auth-token") String token,
                                                              @RequestParam(value = "from") long from,
                                                              @RequestParam(value = "count") long count) {
        List<ResponseBidDto> responseBidDtos = expertService.getExpertBids(token, from, count);
        return ResponseEntity.ok(responseBidDtos);
    }

    @GetMapping(value = "/bids/{bidId}")
    public ResponseEntity<ResponseBidDto> getExpertBid(@RequestHeader("Auth-token") String token,
                                                       @PathVariable("bidId") long bidId) {
        ResponseBidDto responseBidDto = expertService.getExpertBid(token, bidId);
        return ResponseEntity.ok(responseBidDto);
    }

    @GetMapping(value = "/events/{eventId}/bids")
    public ResponseEntity<List<ResponseBidDto>> getExpertEventBids(@RequestHeader("Auth-token") String token,
                                                                   @PathVariable("eventId") long eventId,
                                                                   @RequestParam(value = "from") long from,
                                                                   @RequestParam(value = "count") long count) {
        List<ResponseBidDto> responseBidDtos = expertService.getExpertEventBids(token, eventId, from, count);
        return ResponseEntity.ok(responseBidDtos);
    }

    @PostMapping(value = "/bids/{bidId}/validate")
    public ResponseEntity<ResponseBidDto> validate(@RequestHeader("Auth-token") String token,
                                                   @PathVariable("bidId") long bidId,
                                                   @RequestBody ValidateDto validateDto) {
        ResponseBidDto responseBidDto = expertService.validate(token, bidId, validateDto);
        return ResponseEntity.ok(responseBidDto);
    }

    @PostMapping(value = "/bids/{bidId}/ban")
    public ResponseEntity<ResponseBanDto> banUser(@RequestHeader("Auth-token") String token,
                                                  @PathVariable("bidId") long bidId,
                                                  @RequestParam("comment") String comment) {
        ResponseBanDto responseBanDto = expertService.banUser(token, bidId, comment);
        return ResponseEntity.ok(responseBanDto);
    }

    @GetMapping(value = "/users/bans")
    public ResponseEntity<List<ResponseBanDto>> getBans(@RequestHeader("Auth-token") String token,
                                                        @RequestParam(value = "from") long from,
                                                        @RequestParam(value = "count") long count) {
        List<ResponseBanDto> bans = expertService.getBans(token, from, count);
        return ResponseEntity.ok(bans);
    }

    @PostMapping(value = "/users/bans/{banId}/unban")
    public ResponseEntity<Boolean> unbanUser(@RequestHeader("Auth-token") String token,
                                             @PathVariable("banId") long banId) {
        expertService.unbanUser(token, banId);
        return ResponseEntity.ok(true);
    }
}

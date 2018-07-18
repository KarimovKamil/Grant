package ru.itis.grant.web.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import ru.itis.grant.dto.MailDto;

@Component
public class RestHttpEntity {

    public HttpEntity<String> entity(MailDto mailDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        try {
            json = ow.writeValueAsString(mailDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new HttpEntity<String>(json, headers);
    }
}

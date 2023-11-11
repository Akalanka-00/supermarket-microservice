package com.flashmart.auth.Service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Service
public class MicroServicesConnectorService {

    private final RestTemplate restTemplate;

    public MicroServicesConnectorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> T postAPI(String url, String requestBody, Class<T> responseType) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(new URI(url), HttpMethod.POST, requestEntity, String.class);

        // Check if the response body is null
        if (responseEntity.getBody() == null) {
            return null;  // Or handle it according to your requirements
        }

        // Deserialize the response body
        return restTemplate.postForObject(url, requestEntity, responseType);
    }

    public static <T> T fetchAPI(URL url, Class<T> type) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(url, type);
    }

    public static <T> T fetchAPI(String baseUrl, Long pathVariable, Class<T> type) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String fullUrl = baseUrl + "/" + pathVariable;
        try {
            URL url = new URL(fullUrl);
            return mapper.readValue(url, type);
        } catch (MalformedURLException e) {
            throw new IOException("Malformed URL: " + fullUrl, e);
        }
    }
}

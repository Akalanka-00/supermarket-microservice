package com.flashmart.order.service;

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

    public <T> T postAPI(String apiUrl, Object request, Class<T> responseType) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> httpEntity = new HttpEntity<>(request, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(new URI(apiUrl), HttpMethod.POST, (org.springframework.http.HttpEntity<?>) httpEntity, String.class);

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(responseEntity.getBody(), responseType);
        } catch (IOException e) {
            throw new RuntimeException("Error parsing JSON response", e);
        }
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

package com.flashmart.auth.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class MicroServicesConnectorService {
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
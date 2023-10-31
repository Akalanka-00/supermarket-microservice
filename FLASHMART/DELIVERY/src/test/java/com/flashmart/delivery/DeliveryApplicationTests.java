package com.flashmart.delivery;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flashmart.delivery.Consts.DELIVER_AVAILABILITY;
import com.flashmart.delivery.dto.DeliveryPersonRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class DeliveryApplicationTests {

    @Container
    final static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0.10");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }
    @Test
    void shouldCreateDeliverUser() throws Exception {

        DeliveryPersonRequest deliverUserRequest = DeliveryPersonRequest.builder()
                .id("D0001")
                .availability(DELIVER_AVAILABILITY.UNAVAILABLE)
                .vehicleID("V1101")
                .build();
        String requestDeliveryUserString = objectMapper.writeValueAsString(deliverUserRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/delivery/user")
                .contentType(MediaType.APPLICATION_JSON).content(requestDeliveryUserString)
        ).andExpect(status().isCreated());
    }

}

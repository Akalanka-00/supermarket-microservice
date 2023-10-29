package com.flashmart.delivery.service;


import com.flashmart.delivery.Consts.DELIVER_AVAILABILITY;
import com.flashmart.delivery.dto.DeliverUserRequest;
import com.flashmart.delivery.dto.DeliverUserResponse;
import com.flashmart.delivery.model.DeliverModel;
import com.flashmart.delivery.repository.DeliverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliverService {

    private final DeliverRepository deliverRepository;

    public void createDeliverUser(DeliverUserRequest deliverUser){
        DeliverModel model = DeliverModel.builder()
                .id(deliverUser.getId())
                .availability(DELIVER_AVAILABILITY.UNAVAILABLE)
                .latitude(0)
                .longitude(0)

                .build();

        deliverRepository.save(model);
        log.info("Deliver User has been added", model.getId());

    }


    public List<DeliverUserResponse> getAllDeliverUsers() {
        List<DeliverModel> model = deliverRepository.findAll();

       return model.stream().map(this::mapToDeliverResponse).toList();
    }

    private DeliverUserResponse mapToDeliverResponse(DeliverModel user) {

        return DeliverUserResponse.builder()
                .id(user.getId())
                .availability(user.getAvailability())
                .longitude(user.getLongitude())
                .latitude(user.getLatitude())
                .build();
    }

    public DeliverUserResponse getDeliverUserById(String id) {
        DeliverModel model = deliverRepository.findById(id).orElse(null);
        if(model!=null){
            return DeliverUserResponse.builder()
                    .id(model.getId())
                    .latitude(model.getLatitude())
                    .availability(model.getAvailability())
                    .longitude(model.getLongitude())
                    .build();
        }
        return null;
    }

    public void updateDeliverUserById(String id, DeliverUserRequest deliverUser) {

        DeliverModel existUser = deliverRepository.findById(id).orElse(null);

        if(existUser!=null){
            if(deliverUser.getAvailability()==DELIVER_AVAILABILITY.AVAILABLE) {
                existUser.setAvailability(deliverUser.getAvailability());
            }
            if(deliverUser.getAvailability()==DELIVER_AVAILABILITY.UNAVAILABLE)
                existUser.setAvailability(deliverUser.getAvailability());

            if(deliverUser.getLatitude()!=0 && deliverUser.getLongitude()!=0) {
                existUser.setLatitude(deliverUser.getLatitude());
                existUser.setLongitude(deliverUser.getLongitude());
            }

            deliverRepository.save(existUser);
        }
    }
}

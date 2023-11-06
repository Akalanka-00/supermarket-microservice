package com.flashmart.customer.controller;

import com.flashmart.customer.dto.PointDTO;
import com.flashmart.customer.service.CustomerPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("api/v1/point")
public class CustomerPointController {

    @Autowired
    private CustomerPointService customerPointService;

    // get discounts by customerId
    @GetMapping("/getPoints/{customerId}")
    public PointDTO getPointByCustomerId(@PathVariable Long customerId){
        return customerPointService.getPointByCustomerId(customerId);
    }

}

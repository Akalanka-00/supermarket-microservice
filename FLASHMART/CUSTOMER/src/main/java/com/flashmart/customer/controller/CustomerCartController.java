package com.flashmart.customer.controller;

import com.flashmart.customer.dto.CartDTO;
import com.flashmart.customer.model.Cart;
import com.flashmart.customer.service.CustomerCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("api/v1/cart")
public class CustomerCartController {

    @Autowired
    private CustomerCartService customerCartService;


}

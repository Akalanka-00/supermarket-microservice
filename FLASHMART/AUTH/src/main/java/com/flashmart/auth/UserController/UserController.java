package com.flashmart.auth.UserController;

import com.flashmart.auth.Entity.User;
import com.flashmart.auth.Service.UserService;
import com.flashmart.auth.UserDTO.CustomerDTO;
import com.flashmart.auth.UserDTO.LoginDTO;
import com.flashmart.auth.UserDTO.UserDTO;
import com.flashmart.auth.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("user")

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/save")
    public String saveUser(@RequestBody UserDTO userDTO)
    {
        String id = userService.addUser(userDTO);
        return id;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO)
    {
        LoginResponse loginResponse = userService.logingUser(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }
    @GetMapping(path = "/admins")
    public List<String> getadminnames() {
        List<User> adminUsers = userService.findByType(1000);
        List<String> adminNames = new ArrayList<>();
        for (User user : adminUsers) {
            adminNames.add(user.getEmail());
        }
        return adminNames;
    }

    @GetMapping("/normalUsers")
    public List<String> getnormalusernames() {
        List<User> userUsers = userService.findByType(1010);
        List<String> userNames = new ArrayList<>();
        for (User user : userUsers) {
            userNames.add(user.getEmail());
        }
        return userNames;
    }

    @GetMapping("/customerDetails")
    public List<CustomerDTO> getCustomerDetails() {
        List<User> userUsers = userService.findByType(1010);
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        CustomerDTO customerDTO = new CustomerDTO();
        for (User user : userUsers) {
            customerDTO.setUserid(user.getUserid());
            customerDTO.setUserfname(user.getUserfname());
            customerDTO.setUserlname(user.getUserlname());
            customerDTO.setMobile(user.getMobile());
            customerDTO.setEmail(user.getEmail());

            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

}


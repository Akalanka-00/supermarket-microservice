package com.flashmart.auth.UserController;

import com.flashmart.auth.Entity.User;
import com.flashmart.auth.Repo.UserRepo;
import com.flashmart.auth.Service.MicroServicesConnectorService;
import com.flashmart.auth.Service.UserService;
import com.flashmart.auth.UserDTO.ResponseDTO;
import com.flashmart.auth.UserDTO.UserDetailsDTO;
import com.flashmart.auth.UserDTO.LoginDTO;
import com.flashmart.auth.UserDTO.UserDTO;
import com.flashmart.auth.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("user")

public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MicroServicesConnectorService microServicesConnectorService;

    @Autowired
    private UserRepo userRepo;

    @PostMapping(path = "/save")
    public String saveUser(@RequestBody UserDTO userDTO) {

        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getEmail() == userDTO.getEmail()) {
                return "Already Registered";
            }
        }

        User newUser = userService.addUser(userDTO);

        if (userDTO.getType() == 1000) {
            return ("Admin Registration Successful. User ID: " + newUser.getUserid());
        } else if (userDTO.getType() == 1011) {
            return ("Delivery Person Registration Successful. User ID: " + newUser.getUserid());
        } else {
            try {
                ResponseDTO responseDTO = microServicesConnectorService.fetchAPI("http://localhost:8080/api/v1/cart/setCart", newUser.getUserid(), ResponseDTO.class);
                return ("New Customer Registration Successful. Customer ID: " + newUser.getUserid() +
                        "\n" + responseDTO.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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

    @GetMapping(path = "/allUsers")
    public List<User> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users;
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

    @GetMapping("/userDetailsByID/{userId}")
    public UserDetailsDTO getCustomerDetails(@PathVariable long userId) {
        User user = userRepo.findById(userId).orElse(null);
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
                userDetailsDTO.setUserid(user.getUserid());
                userDetailsDTO.setUserfname(user.getUserfname());
                userDetailsDTO.setUserlname(user.getUserlname());
                userDetailsDTO.setMobile(user.getMobile());
                userDetailsDTO.setEmail(user.getEmail());
        return userDetailsDTO;
    }

    @GetMapping("/userDetails/{userType}")
    public List<UserDetailsDTO> getUserDetails(@PathVariable int userType) {
        List<User> userUsers = userService.findByType(userType);
        List<UserDetailsDTO> userDetailsDTOS = new ArrayList<>();
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        for (User user : userUsers) {
            userDetailsDTO.setUserid(user.getUserid());
            userDetailsDTO.setUserfname(user.getUserfname());
            userDetailsDTO.setUserlname(user.getUserlname());
            userDetailsDTO.setMobile(user.getMobile());
            userDetailsDTO.setEmail(user.getEmail());

            userDetailsDTOS.add(userDetailsDTO);
        }
        return userDetailsDTOS;
    }

}


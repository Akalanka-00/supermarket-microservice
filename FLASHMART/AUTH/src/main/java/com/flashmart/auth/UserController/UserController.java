package com.flashmart.auth.UserController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            if (user.getEmail().equals(userDTO.getEmail())) {
                return "Already Registered";
            }
        }

        User newUser = userService.addUser(userDTO);

        if (userDTO.getType() == 1000) {
            return ("Admin Registration Successful. User ID: " + newUser.getUserid());
        } else if (userDTO.getType() == 1011) {
//            try {
//                ObjectMapper objectMapper = new ObjectMapper();
//
//                Map<String, Object> jsonMap = new HashMap<>();
//                jsonMap.put("availability", 1000);
//                jsonMap.put("vehicleID", null);
//                jsonMap.put("latitude", 0);
//                jsonMap.put("longitude", 0);
//                jsonMap.put("lastUpdatedTime",null );
//                jsonMap.put("total_rating", 0);
//                jsonMap.put("no_of_rated_users", 0);
//
//                String jsonInputString = objectMapper.writeValueAsString(jsonMap);
//                System.out.println(jsonInputString);
//                //http://localhost:8084/api/delivery/user/newUser
//                ResponseDTO responseDTO = microServicesConnectorService.fetchAPI("http://localhost:8084/api/delivery/user/newUser", newUser.getUserid(), ResponseDTO.class);
//                return ("Delivery Person Registration Successful. User ID: " + newUser.getUserid() +
//                        "\n" + responseDTO.getMessage());
//
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }

            try {
                ResponseDTO responseDTO = microServicesConnectorService.fetchAPI("http://localhost:8084/api/delivery/user/newUser", newUser.getUserid(), ResponseDTO.class);
                return ("New Delivery Registration Successful. Customer ID: " + newUser.getUserid() +
                        "\n" + responseDTO.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (userDTO.getType() == 1010) {

            try {
                ResponseDTO responseDTO = microServicesConnectorService.fetchAPI("http://localhost:8080/api/v1/cart/setCart", newUser.getUserid(), ResponseDTO.class);
                return ("New Customer Registration Successful. Customer ID: " + newUser.getUserid() +
                        "\n" + responseDTO.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
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


package com.flashmart.auth.UserController;

import com.flashmart.auth.Entity.User;
import com.flashmart.auth.Service.UserService;
import com.flashmart.auth.UserDTO.UserDetailsDTO;
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

    @GetMapping("/customerDetails/{customerId}")
    public UserDetailsDTO getCustomerDetails(@PathVariable int customerId) {
        List<User> userUsers = userService.findByType(1010);
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        for (User user : userUsers) {
            if(user.getUserid()==customerId){
                userDetailsDTO.setUserid(user.getUserid());
                userDetailsDTO.setUserfname(user.getUserfname());
                userDetailsDTO.setUserlname(user.getUserlname());
                userDetailsDTO.setMobile(user.getMobile());
                userDetailsDTO.setEmail(user.getEmail());
            }
        }
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


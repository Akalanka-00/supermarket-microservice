package com.flashmart.auth.Service;


import com.flashmart.auth.Entity.User;
import com.flashmart.auth.Response.LoginResponse;
import com.flashmart.auth.UserDTO.LoginDTO;
import com.flashmart.auth.UserDTO.UserDTO;

import java.util.List;


public interface UserService {

    String addUser(UserDTO userDTO);

    LoginResponse logingUser(LoginDTO loginDTO);

    List<User> findByType(int type);
}

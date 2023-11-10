package com.flashmart.auth.Service;

import com.flashmart.auth.Entity.User;
import com.flashmart.auth.UserDTO.LoginDTO;
import com.flashmart.auth.UserDTO.UserDTO;
import com.flashmart.auth.response.LoginResponse;


import java.util.List;


public interface UserService {

    User addUser(UserDTO userDTO);

    LoginResponse logingUser(LoginDTO loginDTO);

    List<User> findByType(int type);
}


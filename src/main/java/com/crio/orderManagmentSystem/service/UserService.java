package com.crio.orderManagmentSystem.service;

import com.crio.orderManagmentSystem.model.User;
import java.util.Optional;

public interface UserService {
   
  public User saveUser(com.crio.orderManagmentSystem.dto.UserDTO userDTO);

  public User getByUsername(String username);

  public Optional<User> getUserByEmail(String email);

  public Optional<User> getUserByPhone(String phone);

  public void deleteUser(Long userId);
}

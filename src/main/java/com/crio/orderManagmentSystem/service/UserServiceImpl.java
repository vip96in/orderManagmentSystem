package com.crio.orderManagmentSystem.service;

import com.crio.orderManagmentSystem.dto.UserDTO;
import com.crio.orderManagmentSystem.exception.NoDataFoundException;
import com.crio.orderManagmentSystem.model.Address;
import com.crio.orderManagmentSystem.model.Role;
import com.crio.orderManagmentSystem.model.User;
import com.crio.orderManagmentSystem.repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public User saveUser(UserDTO userDTO) {
    if(userDTO != null) {
    User user = new User();
    user.setUsername(userDTO.getUsername());
    user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    user.setName(userDTO.getName());
    user.setEmail(userDTO.getEmail());
    Address address = new Address();
    address.setFlat(userDTO.getAddress().getFlat());
    address.setStreet(userDTO.getAddress().getStreet());
    address.setArea(userDTO.getAddress().getArea());
    address.setCity(userDTO.getAddress().getCity());
    address.setCountry(userDTO.getAddress().getCountry());
    address.setPostalCode(userDTO.getAddress().getPostalCode());
    user.setAddress(address);
    
    Role role = new Role();
    if(userDTO.getRole().equals(com.crio.orderManagmentSystem.enums.Role.ADMIN)) {
      role.setRole(com.crio.orderManagmentSystem.enums.Role.ADMIN);
    }else {
      role.setRole(com.crio.orderManagmentSystem.enums.Role.CUSTOMER);
    }
    Set<Role> roles = new HashSet<>();
    roles.add(role);
    user.setRoles(roles);

    user.setPhone(userDTO.getPhone());

    return userRepository.save(user);
    } else {
      throw new NoDataFoundException("User data is insufficient!");
    }
    
  }

  @Override
  public User getByUsername(String username) {

    Optional<User> user = userRepository.findByUsername(username);
    if(user.isPresent()) {
      return userRepository.findByUsername(username).get();
    }else {
      throw new NoDataFoundException("No user found with username : "+ username);
    }

  }

  @Override
  public Optional<User> getUserByEmail(String email) {
    
    Optional<User> user = userRepository.findByEmail(email);
    if(user.isPresent()) {
      return userRepository.findByEmail(email);
    }else {
      throw new NoDataFoundException("No user found with username : "+ email);
    }

  }

  @Override
  public Optional<User> getUserByPhone(String phone) {
    
    Optional<User> user = userRepository.findByPhone(phone);
    if(user.isPresent()) {
      return userRepository.findByPhone(phone);
    }else {
      throw new NoDataFoundException("No user found with username : "+ phone);
    }

  }

  @Override
  public void deleteUser(Long userId) {
    Optional<User> user = userRepository.findById(userId);
    
    if(user.isPresent()) {
      userRepository.delete(user.get());
    } else {
      throw new NoDataFoundException("No user found with user id : "+userId);
    }
  }
 
}

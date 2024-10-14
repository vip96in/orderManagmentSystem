package com.crio.orderManagmentSystem.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crio.orderManagmentSystem.dto.UserDTO;
import com.crio.orderManagmentSystem.exception.InvalidInputException;
import com.crio.orderManagmentSystem.exception.NoDataFoundException;
import com.crio.orderManagmentSystem.exception.ResourceNotFoundException;
import com.crio.orderManagmentSystem.model.Customer;
import com.crio.orderManagmentSystem.model.Role;
import com.crio.orderManagmentSystem.model.User;
import com.crio.orderManagmentSystem.repository.CustomerRepository;
import com.crio.orderManagmentSystem.repository.UserRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public List<Customer> findAll() {

    List<Customer> allCustomers = customerRepository.findAll();
    if(!allCustomers.isEmpty())
      return customerRepository.findAll();
    throw new NoDataFoundException("No customers found!");

  }

  @Override
  public Customer findById(Long id) {
    if(id == null)
      throw new InvalidInputException("Invalid customer id :" +id);
    return customerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No customer found with id : "+id));
  }

  @Override
  public Customer save(UserDTO userDTO) {
    if(userDTO == null)
      throw new InvalidInputException("Invalid user input for customer type!");
      Customer customer = new Customer();
      customer.setName(userDTO.getName());
      customer.setEmail(userDTO.getEmail());

      com.crio.orderManagmentSystem.model.Address address = new com.crio.orderManagmentSystem.model.Address();
      address.setFlat(userDTO.getAddress().getFlat());
      address.setStreet(userDTO.getAddress().getStreet());
      address.setArea(userDTO.getAddress().getArea());
      address.setCity(userDTO.getAddress().getCity());
      address.setCountry(userDTO.getAddress().getCountry());
      address.setPostalCode(userDTO.getAddress().getPostalCode());
      customer.setAddress(address);

      customer.setPhone(userDTO.getPhone());
      Optional<User> user = userRepository.findByUsername(userDTO.getUsername());
      if(userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
        customer.setUser(user.get());
      }else {
        User newUser = new com.crio.orderManagmentSystem.model.User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setName(userDTO.getName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setAddress(address);
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setRole(com.crio.orderManagmentSystem.enums.Role.CUSTOMER);
        roles.add(role);
        newUser.setRoles(roles);
        newUser.setPhone(userDTO.getPhone());
        customer.setUser(newUser);
        userRepository.save(newUser);
      }      
      customer.setOrders(new ArrayList<>());
      return customerRepository.save(customer);
  }

  @Override
  public void delete(Long id) {
    if(id == null)
      throw new InvalidInputException("Invalid customer id : "+id);
    customerRepository.delete(customerRepository.findById(id).get());  
  }
  
}

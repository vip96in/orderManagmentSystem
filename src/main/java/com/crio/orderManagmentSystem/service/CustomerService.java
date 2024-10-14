package com.crio.orderManagmentSystem.service;
import java.util.List;
import com.crio.orderManagmentSystem.model.Customer;
import com.crio.orderManagmentSystem.dto.UserDTO;

public interface CustomerService {

  public List<Customer> findAll();

  public Customer findById(Long id);

  public Customer save(UserDTO userDTO);

  public void delete(Long id);
}

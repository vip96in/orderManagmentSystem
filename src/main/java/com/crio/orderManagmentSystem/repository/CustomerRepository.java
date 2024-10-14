package com.crio.orderManagmentSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crio.orderManagmentSystem.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}

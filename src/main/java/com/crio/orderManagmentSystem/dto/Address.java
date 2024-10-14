package com.crio.orderManagmentSystem.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

  private String flat;

  private String street;

  private String area;

  private String city;

  private String country;

  private String postalCode;
  
}

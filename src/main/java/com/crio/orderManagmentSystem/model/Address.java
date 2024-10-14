package com.crio.orderManagmentSystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

  @Column(name = "flat", nullable = false, unique = false)
  private String flat;

  @Column(name = "street", nullable = false, unique = false)
  private String street;

  @Column(name = "area", nullable = false, unique = false)
  private String area;

  @Column(name = "city", nullable = false, unique = false)
  private String city;

  @Column(name = "country", nullable = false, unique = false)
  private String country;

  @Column(name = "postal_code", nullable = false, unique = false)
  private String postalCode;
  
}

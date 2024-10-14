package com.crio.orderManagmentSystem.model;

import com.crio.orderManagmentSystem.enums.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "grocery_items")
public class GroceryItem {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false, unique = false)
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(name = "category", nullable = false, unique = false)
  private Category category;

  @Column(name = "price", nullable = false, unique = false)
  private Double price;

  @Column(name = "quantity", nullable = false, unique = false)
  private Integer quantity;

  @ManyToMany(mappedBy = "groceryItems")
  private List<Order> orders = new ArrayList<>();

}

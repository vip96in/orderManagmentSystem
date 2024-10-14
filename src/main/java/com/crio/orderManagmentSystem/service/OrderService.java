package com.crio.orderManagmentSystem.service;

import java.util.List;

import com.crio.orderManagmentSystem.model.Order;

public interface OrderService {

  public Order placeOrder(Long customerId, List<Long> groceryItemIds, double totalPrice);

  public Order getOrderById(Long orderId);

  public List<Order> getOrdersByCustomerId(Long customerId);

  public void cancelOrder(Long orderId);
}

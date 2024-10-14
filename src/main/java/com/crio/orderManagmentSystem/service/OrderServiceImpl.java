package com.crio.orderManagmentSystem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.orderManagmentSystem.exception.NoDataFoundException;
import com.crio.orderManagmentSystem.model.Customer;
import com.crio.orderManagmentSystem.model.GroceryItem;
import com.crio.orderManagmentSystem.model.Order;
import com.crio.orderManagmentSystem.repository.CustomerRepository;
import com.crio.orderManagmentSystem.repository.GroceryItemRepository;
import com.crio.orderManagmentSystem.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{

   @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private GroceryItemRepository groceryItemRepository;

  @Override
  public Order placeOrder(Long customerId, List<Long> groceryItemIds, double totalPrice) {
    
    Optional<Customer> customer = customerRepository.findById(customerId);
    
    Order order = new Order();

    if(customer.isPresent()) {
      Customer existingCustomer = customer.get();
      order.setCustomer(existingCustomer);
    }else {
      throw new NoDataFoundException("No customer found with id : "+customerId);
    }

    List<GroceryItem> groceryItems = groceryItemRepository.findAllById(groceryItemIds);

    if(groceryItemIds != null) {
      order.setGroceryItems(groceryItems);
    }else {
      throw new NoDataFoundException("No grocery items found!");
    }

    order.setOrderDate(LocalDateTime.now());
    order.setTotalPrice(totalPrice);

    return orderRepository.save(order);
  }

  @Override
  public Order getOrderById(Long orderId) {
    return orderRepository.findById(orderId).orElseThrow(()-> new NoDataFoundException("No order found with id : "+orderId));
  }

  @Override
  public List<Order> getOrdersByCustomerId(Long customerId) {
    List<Order> orders = orderRepository.findOrdersByCustomerId(customerId);
    if(orders != null)
      return orders;
    else
      throw new NoDataFoundException("No orders found with customer id : "+customerId);  
  }

  @Override
  public void cancelOrder(Long orderId) {
    Optional<Order> order = orderRepository.findById(orderId);
    if(order.isPresent())
      orderRepository.delete(order.get());
    else
      throw new NoDataFoundException("No order found with id : "+orderId);  
  }
  
}

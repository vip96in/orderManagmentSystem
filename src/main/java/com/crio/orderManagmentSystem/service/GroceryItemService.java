package com.crio.orderManagmentSystem.service;

import com.crio.orderManagmentSystem.model.GroceryItem;
import java.util.List;
import java.util.Optional;
public interface GroceryItemService {

   public GroceryItem saveGroceryItem(GroceryItem groceryItem);

    public List<GroceryItem> getAllGroceryItems();

    public Optional<GroceryItem> getGroceryItemById(Long id);

    public void deleteGroceryItem(Long id);

}

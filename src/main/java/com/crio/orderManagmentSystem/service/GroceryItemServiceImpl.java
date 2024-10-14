package com.crio.orderManagmentSystem.service;

import com.crio.orderManagmentSystem.exception.InvalidInputException;
import com.crio.orderManagmentSystem.exception.NoDataFoundException;
import com.crio.orderManagmentSystem.model.GroceryItem;
import com.crio.orderManagmentSystem.repository.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroceryItemServiceImpl implements GroceryItemService{

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @Override
    public GroceryItem saveGroceryItem(GroceryItem groceryItem) {
        return groceryItemRepository.save(groceryItem);
    }

    @Override
    public List<GroceryItem> getAllGroceryItems() {
        List<GroceryItem> groceryItems = groceryItemRepository.findAll();
        if(groceryItems.isEmpty())
            throw new NoDataFoundException("No grocery items available!");
        return groceryItemRepository.findAll();
    }

    @Override
    public Optional<GroceryItem> getGroceryItemById(Long id) {
        if(id == null)
            throw new InvalidInputException("Not a valid item id : "+id);
        return groceryItemRepository.findById(id);
    }

    @Override
    public void deleteGroceryItem(Long id) {
        if(id == null)
            throw new InvalidInputException("Not a valid item id : "+id);
        groceryItemRepository.deleteById(id);
    }

}

package com.crio.orderManagmentSystem.controller;

import com.crio.orderManagmentSystem.model.GroceryItem;
import com.crio.orderManagmentSystem.service.GroceryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grocery-items")
public class GroceryItemController {

    @Autowired
    private GroceryItemService groceryItemService;

    // Save a Grocery Item
    @PostMapping
    public ResponseEntity<GroceryItem> saveGroceryItem(@RequestBody GroceryItem groceryItem) {
        GroceryItem savedItem = groceryItemService.saveGroceryItem(groceryItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }

    // Get All Grocery Items
    @GetMapping
    public ResponseEntity<List<GroceryItem>> getAllGroceryItems() {
        List<GroceryItem> groceryItems = groceryItemService.getAllGroceryItems();
        return ResponseEntity.ok(groceryItems);
    }

    // Get Grocery Item by ID
    @GetMapping("/{id}")
    public ResponseEntity<GroceryItem> getGroceryItemById(@PathVariable Long id) {
        Optional<GroceryItem> groceryItem = groceryItemService.getGroceryItemById(id);
        return groceryItem.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Delete a Grocery Item
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGroceryItem(@PathVariable Long id) {
        groceryItemService.deleteGroceryItem(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Grocery item deleted successfully.");
    }
}

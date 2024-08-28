package com.example.inventory.controller;

import com.example.inventory.dto.InventoryDTO;
import com.example.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    
    @GetMapping("/getinventories")
    public List<InventoryDTO> getInventories() {
        return inventoryService.getAllInventory();
    }
    
    @PostMapping("/addinventory")
    public InventoryDTO addInventory(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.createInventory(inventoryDTO);
    }
    
    @PutMapping("/updateinventory")
    public InventoryDTO updateInventory(@RequestBody  InventoryDTO inventoryDTO) {
        return inventoryService.updateInventory(inventoryDTO);
    }
    
    @DeleteMapping("/deleteinventory/{inventoryId}")
    public String deleteInventory(@PathVariable  int inventoryId) {
        return inventoryService.deleteInventory(inventoryId);
    }
    
    @GetMapping("/getinventory/{inventoryId}")
    public InventoryDTO getInventory(@PathVariable int inventoryId) {
        return inventoryService.getInventoryById(inventoryId);
    }
}

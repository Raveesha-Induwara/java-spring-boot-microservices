package com.example.inventory.service;

import com.example.inventory.dto.InventoryDTO;
import com.example.inventory.model.Inventory;
import com.example.inventory.repo.InventoryRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class InventoryService {
    @Autowired
    private InventoryRepo inventoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    
    public List<InventoryDTO> getItems() {
        List<Inventory> productList = inventoryRepo.findAll();
        return modelMapper.map(productList, new TypeToken<List<InventoryDTO>>(){}.getType());
    }
    
    public InventoryDTO addItem(InventoryDTO inventoryDTO) {
        inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO;
    }
    
    public InventoryDTO updateItem(InventoryDTO inventoryDTO) {
        inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO;
    }
    
    public String deleteItem(Integer inventoryId) {
        inventoryRepo.deleteById(inventoryId);
        return "Item deleted";
    }
    
    public InventoryDTO getItemById(Integer itemId) {
        Inventory item = inventoryRepo.getItemById(itemId);
        return modelMapper.map(item, InventoryDTO.class);
    }
}

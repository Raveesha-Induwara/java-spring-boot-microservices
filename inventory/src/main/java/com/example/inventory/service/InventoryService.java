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
    
    public List<InventoryDTO> getAllInventory() {
        List<Inventory> productList = inventoryRepo.findAll();
        return modelMapper.map(productList, new TypeToken<List<InventoryDTO>>(){}.getType());
    }
    
    public InventoryDTO createInventory(InventoryDTO orderDTO) {
        inventoryRepo.save(modelMapper.map(orderDTO, Inventory.class));
        return orderDTO;
    }
    
    public InventoryDTO updateInventory(InventoryDTO orderDTO) {
        inventoryRepo.save(modelMapper.map(orderDTO, Inventory.class));
        return orderDTO;
    }
    
    public String deleteInventory(Integer orderId) {
        inventoryRepo.deleteById(orderId);
        return "Inventory deleted";
    }
    
    public InventoryDTO getInventoryById(Integer orderId) {
        Inventory order = inventoryRepo.getInventoryById(orderId);
        return modelMapper.map(order, InventoryDTO.class);
    }
}

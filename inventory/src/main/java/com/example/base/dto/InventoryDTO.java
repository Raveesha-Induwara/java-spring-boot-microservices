package com.example.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InventoryDTO {
    private int id;
    private int itemId;
    private int productId;
    private int quantity;
}

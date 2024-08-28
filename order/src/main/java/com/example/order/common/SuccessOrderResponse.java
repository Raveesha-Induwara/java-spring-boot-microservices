package com.example.order.common;

import com.example.order.dto.OrderDTO;
import lombok.Getter;

@Getter
public class SuccessOrderResponse implements OrderResponse{
    private final OrderDTO order;
    
    public SuccessOrderResponse(OrderDTO order) {
        this.order = order;
    }
}

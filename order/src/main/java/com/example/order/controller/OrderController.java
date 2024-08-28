package com.example.order.controller;

import com.example.order.dto.OrderDTO;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class OrderController {
    @Autowired
    private OrderService orderService;
    
    @GetMapping("/getorders")
    public List<OrderDTO> getOrders() {
        return orderService.getAllOrders();
    }
    
    @PostMapping("/addorder")
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }
    
    @PutMapping("/updateorder")
    public OrderDTO updateOrder(@RequestBody  OrderDTO orderDTO) {
        return orderService.updateOrder(orderDTO);
    }
    
    @DeleteMapping("/deleteorder/{orderId}")
    public String deleteOrder(@PathVariable  int orderId) {
        return orderService.deleteOrder(orderId);
    }
    
    @GetMapping("/getorder/{orderId}")
    public OrderDTO getOrder(@PathVariable int orderId) {
        return orderService.getOrderById(orderId);
    }
}

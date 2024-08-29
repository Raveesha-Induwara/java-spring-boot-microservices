package com.example.order.service;

import com.example.inventory.dto.InventoryDTO;
import com.example.order.common.ErrorOrderResponse;
import com.example.order.common.OrderResponse;
import com.example.order.common.SuccessOrderResponse;
import com.example.order.dto.OrderDTO;
import com.example.order.model.Order;
import com.example.order.repo.OrderRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Transactional
public class OrderService {
    private final WebClient webClient;
    
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ModelMapper modelMapper;
    
    public OrderService(WebClient.Builder webClientBuilder, ModelMapper modelMapper, OrderRepo orderRepo) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/api/v1").build();
        this.orderRepo = orderRepo;
        this.modelMapper = modelMapper;
    }
    
    public List<OrderDTO> getAllOrders() {
        List<Order> productList = orderRepo.findAll();
        return modelMapper.map(productList, new TypeToken<List<OrderDTO>>(){}.getType());
    }
    
    public OrderResponse createOrder(OrderDTO orderDTO) {
        Integer itemId = orderDTO.getItemId();
        try{
            InventoryDTO inventoryResponse = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/item/{itemId}").build(itemId))
                    .retrieve()
                    .bodyToMono(InventoryDTO.class)
                    .block();
            
            assert inventoryResponse != null;
            if(inventoryResponse.getQuantity() > 0) {
                orderRepo.save(modelMapper.map(orderDTO, Order.class));
                return new SuccessOrderResponse(orderDTO);
            }
            else {
                return new ErrorOrderResponse("Item not available, please try later");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        orderRepo.save(modelMapper.map(orderDTO, Order.class));
        return orderDTO;
    }
    
    public String deleteOrder(Integer orderId) {
        orderRepo.deleteById(orderId);
        return "Order deleted";
    }
    
    public OrderDTO getOrderById(Integer orderId) {
        Order order = orderRepo.getOrderById(orderId);
        return modelMapper.map(order, OrderDTO.class);
    }
}

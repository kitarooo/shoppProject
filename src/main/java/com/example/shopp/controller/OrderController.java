package com.example.shopp.controller;

import com.example.shopp.dto.Enums.MethodOfPurchases;
import com.example.shopp.dto.OrderDTO;
import com.example.shopp.dto.OrderDetailsDTO;
import com.example.shopp.dto.info.OrderInfo;
import com.example.shopp.entity.Order;
import com.example.shopp.entity.OrderDetails;
import com.example.shopp.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
@Transactional
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity<Object> createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }

    @GetMapping("/allOrders")
    public List<OrderInfo> allOrders() {
        return orderService.allOrders();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable Long id) {
        return orderService.deleteOrder(id);
    }
}

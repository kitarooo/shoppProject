package com.example.shopp.controller;

import com.example.shopp.dto.OrderDTO;
import com.example.shopp.dto.info.OrderInfo;
import com.example.shopp.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
@Transactional
@Tag(name = "Order controller", description = "Uses for logic upon orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/createOrder")
    @PreAuthorize("hasAnyAuthority('USER')")
    @Operation(summary = "make order", description = "create order for user")
    public ResponseEntity<Object> createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }

    @GetMapping("/allOrders")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "get all orders", description = "Admin can get all orders")
    public List<OrderInfo> allOrders() {
        return orderService.allOrders();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable Long id) {
        return orderService.deleteOrder(id);
    }
}

package com.example.shopp.service;

import com.example.shopp.dto.OrderDTO;
import com.example.shopp.dto.OrderDetailsDTO;
import com.example.shopp.entity.Order;
import com.example.shopp.entity.OrderDetails;
import com.example.shopp.entity.Product;
import com.example.shopp.exception.NotFoundException;
import com.example.shopp.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;

    public ResponseEntity<Object> createOrder(OrderDTO orderDTO) {
        Model model = new Model();
        List<OrderDetails> orderDetails = parseOrderDTOtoOrderDetails(orderDTO.getDetails());
        double totalSum = 0.0;

        for (OrderDetails details : orderDetails) {
            totalSum += details.getTotalPrice();
        }

        Order order = Order.builder()
                .details(orderDetails)
                .totalPrice(totalSum)
                .address(orderDTO.getAddress())
                .methodOfPurchases(orderDTO.getMethodOfPurchases())
                .user(userService.checkUserOnExistAndReturn(orderDTO.getUserId()))
                .build();

        orderRepository.save(order);
        model.setResult("Order was created!");

        return ResponseEntity.ok(model.getResult());
    }

    public ResponseEntity<Object> deleteOrderById(Long orderId) {
        orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order was not found!"));
        orderRepository.deleteById(orderId);
        Model model = new Model();
        model.setResult("Order was deleted!");
        return ResponseEntity.ok(model.getResult());
    }


    public List<OrderDetails> parseOrderDTOtoOrderDetails(List<OrderDetailsDTO> orderDTOS) {
        List<OrderDetails> orderDetails = new ArrayList<>();

        for (OrderDetailsDTO details : orderDTOS) {
            Product product = productService.checkProductOnExistAndReturn(details.getProductId());
            orderDetails.add(OrderDetails.builder()
                    .product(product)
                    .totalQuantity(productService.getAllQuantity())
 /*-*/              .totalPrice(product.getQuantity() * product.getProductPrice())
                    .build());
        }

        return orderDetails;
    }
}

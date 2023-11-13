package com.example.shopp.service;

import com.example.shopp.dto.OrderDTO;
import com.example.shopp.dto.OrderDetailsDTO;
import com.example.shopp.dto.info.OrderInfo;
import com.example.shopp.entity.Order;
import com.example.shopp.entity.OrderDetails;
import com.example.shopp.entity.Product;
import com.example.shopp.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;

    public List<OrderInfo> allOrders() {
        return mapOrderToOrderINFO(orderRepository.findAll());
    }

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

    public ResponseEntity<Object> deleteOrder(Long orderId) {
        Model model = new Model();

        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            orderRepository.delete(order);
            model.setResult("Order with ID " + orderId + " was deleted!");

            return ResponseEntity.ok(model.getResult());
        } else {
            model.setResult("Order with ID " + orderId + " not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(model.getResult());
        }
    }


    public List<OrderDetails> parseOrderDTOtoOrderDetails(List<OrderDetailsDTO> orderDTOS) {
        List<OrderDetails> orderDetails = new ArrayList<>();

        for (OrderDetailsDTO details : orderDTOS) {
            Product product = productService.checkProductOnExistAndReturn(details.getProductId());
            orderDetails.add(OrderDetails.builder()
                    .product(product)
                    .totalQuantity(productService.getAllQuantity())
                    /*-*/.totalPrice(product.getQuantity() * product.getProductPrice())
                    .build());
        }

        return orderDetails;
    }

    public List<OrderInfo> mapOrderToOrderINFO(List<Order> orders) {
        List<OrderInfo> orderDTOS = new ArrayList<>();
        for (Order order : orders) {
            orderDTOS.add(OrderInfo.builder()
                    .userId(order.getUser().getUserId())
                    .address(order.getAddress())
                    .totalPrice(order.getTotalPrice())
                    .methodOfPurchases(order.getMethodOfPurchases())
                    .build());
        }

        return orderDTOS;
    }
}

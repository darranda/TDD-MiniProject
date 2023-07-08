package com.TDDMiniProject.TDD.Controller;

import com.TDDMiniProject.TDD.Model.Order;
import com.TDDMiniProject.TDD.Repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@Validated
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //Get all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return ResponseEntity.ok(orders);
    }

    //Create new order
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody @Valid Order order) {
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }


    //Update an order
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody @Valid Order updatedOrder) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.map(order -> {
            order.setCustomerName(updatedOrder.getCustomerName());
            order.setShippingAddress(updatedOrder.getShippingAddress());
            order.setTotal(updatedOrder.getTotal());
            Order savedOrder = orderRepository.save(order);
            return ResponseEntity.ok(savedOrder);
        }).orElse(ResponseEntity.notFound().build());
    }

//Delete order by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            orderRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Handles validation errors (Exception)
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(Exception exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}

package com.TDDMiniProject.TDD;

import com.TDDMiniProject.TDD.Model.Order;
import com.TDDMiniProject.TDD.Repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class OrderRepositoryTests {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testSaveOrder() {
        // Create a new Order object
        Order order = new Order(001L,"Larry Smith", LocalDate.now(), "123 Wallaby Way", 150.0);

        // Save the order
        Order savedOrder = orderRepository.save(order);

        // Verify that the order is saved with a non-null ID
        assertNotNull(savedOrder.getId());
    }


    @Test
    public void testGetOrderById() {
        // Create a new Order object
        Order order = new Order(002L, "John Dew", LocalDate.now(), "400 Washington Ave", 400.0);

        // Save the order
        Order savedOrder = orderRepository.save(order);

        // Get the order by ID
        Order retrievedOrder = orderRepository.findById(savedOrder.getId()).orElse(null);

        // Verify that the retrieved order is not null
        assertNotNull(retrievedOrder);

        // Verify that the retrieved order matches the saved order
        assertEquals(savedOrder.getId(), retrievedOrder.getId());
        assertEquals(savedOrder.getCustomerName(), retrievedOrder.getCustomerName());
        assertEquals(savedOrder.getOrderDate(), retrievedOrder.getOrderDate());
        assertEquals(savedOrder.getShippingAddress(), retrievedOrder.getShippingAddress());
        assertEquals(savedOrder.getTotal(), retrievedOrder.getTotal());
    }

    @Test
    public void testUpdateOrder() {
        // Create a new Order object
        Order order = new Order(001L, "Larry Smith", LocalDate.now(), "Wallaby Way", 100.0);

        // Save the order
        Order savedOrder = orderRepository.save(order);

        // Modify the saved order
        savedOrder.setCustomerName("Jane Smith");
        savedOrder.setTotal(200.0);

        // Update the order
        Order updatedOrder = orderRepository.save(savedOrder);

        // Verify that the updated order matches the modifications
        assertEquals("Jane Smith", updatedOrder.getCustomerName());
        assertEquals(200.0, updatedOrder.getTotal());
    }

    @Test
    public void testDeleteOrder() {
        // Create a new Order object
        Order order = new Order(002L, "John Dew", LocalDate.now(), "400 Washington Ave", 10.0);

        // Save the order
        Order savedOrder = orderRepository.save(order);

        // Delete the order
        orderRepository.delete(savedOrder);

        // Try to retrieve the deleted order
        Order retrievedOrder = orderRepository.findById(savedOrder.getId()).orElse(null);

        // Verify that the retrieved order is null
        assertNull(retrievedOrder);
    }
}

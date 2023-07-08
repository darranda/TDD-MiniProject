package com.TDDMiniProject.TDD;

import com.TDDMiniProject.TDD.Controller.OrderController;
import com.TDDMiniProject.TDD.Model.Order;
import com.TDDMiniProject.TDD.Repository.OrderRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc
class OrderControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void testCreateOrder() throws Exception {
        // Prepare mock order
        Order order = new Order(1L, "John Dew", LocalDate.now(), "400 Washington Ave", 100.0);
        when(orderRepository.save(order)).thenReturn(order);

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerName\":\"John Dew\",\"orderDate\":\"" + LocalDate.now() + "\",\"shippingAddress\":\"400 Washington Ave\",\"total\":100.0}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGetOrderById() throws Exception {
        // Set up a mock order
        Order order = new Order(1L, "John Dew", LocalDate.now(), "400 Washington Ave", 100.0);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        // Perform GET request with a valid order ID
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateOrder() throws Exception {
        // Set up a mock order
        Order order = new Order(1L, "John Dew", LocalDate.now(), "400 Washington Ave", 100.0);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        // Perform PUT request with a valid order ID
        mockMvc.perform(MockMvcRequestBuilders.put("/orders/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerName\":\"John Dew\",\"orderDate\":\"" + LocalDate.now() + "\",\"shippingAddress\":\"400 Washington Ave\",\"total\":100.0}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteOrder() throws Exception {
        // Set up a mock order
        Order order = new Order(1L, "John Dew", LocalDate.now(), "400 Washington Ave", 100.0);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        // Perform delete with a valid order ID
        mockMvc.perform(MockMvcRequestBuilders.delete("/orders/{id}", 1))
                .andExpect(status().isOk());
    }
// Validation Tests

@Test
        public void testCreateOrder_ValidData() throws Exception {
            // Prepare valid order data
            String validOrderData = "{\"customerName\":\"John Dew\",\"orderDate\":\"2023-07-07\",\"shippingAddress\":\"400 Washington Ave\",\"total\":100.0}";

            // Perform the POST request
            mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(validOrderData))
                    .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    // Order not found tests

    @Test
    public void testGetOrderById_OrderNotFound() throws Exception {
        // Simulate an order not found scenario
        Long orderId = 1L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Perform GET request for a non-existent order
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/{id}", orderId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testUpdateOrder_OrderNotFound() throws Exception {
        // Simulate an order not found scenario
        Long orderId = 1L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Perform PUT request for a non-existent order
        mockMvc.perform(MockMvcRequestBuilders.put("/orders/{id}", orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerName\":\"John Dew\",\"orderDate\":\"" + LocalDate.now() + "\",\"shippingAddress\":\"400 Washington Ave\",\"total\":100.0}"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testDeleteOrder_OrderNotFound() throws Exception {
        // Simulate an order not found scenario
        Long orderId = 1L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Perform DELETE request for a non-existent order
        mockMvc.perform(MockMvcRequestBuilders.delete("/orders/{id}", orderId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
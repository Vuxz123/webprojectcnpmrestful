package com.ethnicthv.webprojectcnpmrestful.data.service;

import com.ethnicthv.webprojectcnpmrestful.data.entity.Order;
import com.ethnicthv.webprojectcnpmrestful.data.entity.OrderState;
import com.ethnicthv.webprojectcnpmrestful.data.entity.Product;
import com.ethnicthv.webprojectcnpmrestful.data.entity.io.OrderIO;
import com.ethnicthv.webprojectcnpmrestful.data.repository.OrderRepository;
import com.ethnicthv.webprojectcnpmrestful.data.repository.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order createOrder(Order order) {
        OrderIO orderIO = new OrderIO(order);
        orderIO = orderRepository.save(orderIO);
        return convertToOrder(orderIO);
    }

    public Order getOrder(Long id) {
        Optional<OrderIO> optionalOrder = orderRepository.findById(id);
        return optionalOrder.map(this::convertToOrder).orElse(null);
    }

    public OrderState getOrderState(Long id) {
        Optional<OrderIO> optionalOrder = orderRepository.findById(id);
        return optionalOrder.map(OrderIO::getState).orElse(null);
    }

    public void updateOrderState(Long id, OrderState state) {
        Optional<OrderIO> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            OrderIO orderIO = optionalOrder.get();
            if (state != orderIO.getState()) {
                orderIO.setState(state);
                orderRepository.save(orderIO);
            }
        }
    }

    @NotNull
    private Order convertToOrder(@NotNull OrderIO orderIO) {
        Map<Long, Integer> productIds = orderIO.getProducts();
        Map<Product, Integer> products = new HashMap<>();
        productIds.forEach((aLong, integer) -> {
            var temp = productRepository.findById(aLong);
            temp.ifPresent(product -> products.put(product, integer));
        });

        return new Order(orderIO.getId(), orderIO.getCustomer_id(), orderIO.getDest(), orderIO.getSrc(), orderIO.getOrderDate(), orderIO.getState(), products);
    }
}


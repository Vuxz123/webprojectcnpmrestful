package com.ethnicthv.webprojectcnpmrestful.controler;

import com.ethnicthv.webprojectcnpmrestful.data.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/order")
public class OrderControler {
    private final OrderService orderService;

    @Autowired
    public OrderControler(OrderService orderService) {
        this.orderService = orderService;
    }
}

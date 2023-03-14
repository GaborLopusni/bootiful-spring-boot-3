package com.bootiful.controller;

import com.bootiful.record.Customer;
import com.bootiful.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
@ResponseBody
public class CustomerHttpController {

    private final CustomerService customerService;

    CustomerHttpController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    Collection<Customer> getAll() {
        return this.customerService.getAll();
    }

    @GetMapping("/customers/{name}")
    Customer getByName(@PathVariable("name") String name) {
        return this.customerService.getByName(name);
    }
}

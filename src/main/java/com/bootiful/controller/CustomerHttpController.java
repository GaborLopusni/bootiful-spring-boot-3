package com.bootiful.controller;

import com.bootiful.record.Customer;
import com.bootiful.service.CustomerService;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
@ResponseBody
public class CustomerHttpController {

    private final CustomerService customerService;

    private final ObservationRegistry observationRegistry;

    public CustomerHttpController(CustomerService customerService, ObservationRegistry observationRegistry) {
        this.customerService = customerService;
        this.observationRegistry = observationRegistry;
    }

    @GetMapping("/customers")
    Collection<Customer> getAll() {
        return this.customerService.getAll();
    }

    @GetMapping("/customers/{name}")
    Customer getByName(@PathVariable("name") String name) {
        Assert.state(Character.isUpperCase(name.charAt(0)), "The name must start with a capital letter.");
        return Observation
                .createNotStarted("byName", this.observationRegistry)
                .observe(() -> this.customerService.getByName(name));
    }
}

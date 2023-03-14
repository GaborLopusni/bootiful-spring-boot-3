package com.bootiful.service;

import com.bootiful.record.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomerService {

    private final JdbcTemplate template;

    private final RowMapper<Customer> customerRowMapper =
            (rs, rowNum) -> new Customer(rs.getInt("id"), rs.getString("name"));


    public CustomerService(JdbcTemplate template) {
        this.template = template;
    }

    Customer getById(Integer id) {
        return this.template.queryForObject("select * from customers where id = ?", customerRowMapper, id);
    }

    Collection<Customer> getAll() {
        return this.template.query("Select * from customers", customerRowMapper);
    }


}

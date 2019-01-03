package com.netcracker.crud.dao;

import com.netcracker.crud.model.Customer;

import java.util.List;

public interface ICustomerDAO {

    void saveCustomer(Customer entity);

    List<Customer> findAllCustomers();

    Customer findCustomerById(int id);

    int deleteCustomerById(int id);

    int deleteAll();
}

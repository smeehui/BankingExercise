package com.huy.app.repository.customer;

import com.huy.app.model.Customer;
import com.huy.app.repository.IGeneralRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICustomerRepository extends PagingAndSortingRepository<Customer,Long> {
}
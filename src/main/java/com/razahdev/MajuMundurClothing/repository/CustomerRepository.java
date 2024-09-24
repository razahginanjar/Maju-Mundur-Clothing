package com.razahdev.MajuMundurClothing.repository;

import com.razahdev.MajuMundurClothing.entities.Customer;
import com.razahdev.MajuMundurClothing.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findCustomerByUsersCustomer(Users usersCustomer);
}

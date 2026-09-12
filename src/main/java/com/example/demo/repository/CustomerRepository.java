package com.example.demo.repository;

import com.example.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Search by name (case-insensitive, partial match)
    List<Customer> findByNameContainingIgnoreCase(String name);

    // Search by phone (partial match)
    List<Customer> findByPhoneContaining(String phone);

    // Check if phone already exists (for duplicate validation)
    boolean existsByPhone(String phone);

    // Check if phone exists but belongs to a different customer (for update)
    boolean existsByPhoneAndIdNot(String phone, Long id);
}

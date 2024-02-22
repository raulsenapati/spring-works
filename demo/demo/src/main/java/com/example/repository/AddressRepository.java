package com.example.repository;

import com.example.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author rahul
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByCityAndStreet(String city, String street);
}

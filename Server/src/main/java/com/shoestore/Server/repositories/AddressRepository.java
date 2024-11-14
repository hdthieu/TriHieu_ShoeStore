package com.shoestore.Server.repositories;

import com.shoestore.Server.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}

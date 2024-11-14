package com.shoestore.Server.repositories;

import com.shoestore.Server.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}

package com.shoestore.Server.repositories;

import com.shoestore.Server.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}

package com.backendeindopdracht.bartdegraaf.repository;

import com.backendeindopdracht.bartdegraaf.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

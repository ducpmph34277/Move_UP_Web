package com.project.move_up_web.repositories;

import com.project.move_up_web.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
  Users findByEmail(String email);
}

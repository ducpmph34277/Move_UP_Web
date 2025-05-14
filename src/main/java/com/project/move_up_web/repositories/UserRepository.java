package com.project.move_up_web.repositories;

import com.project.move_up_web.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
  @Query("SELECT u FROM Users u WHERE u.email = ?1")
  Optional<Users> findByEmail(String email);

//  User findByEmail(String email);
}

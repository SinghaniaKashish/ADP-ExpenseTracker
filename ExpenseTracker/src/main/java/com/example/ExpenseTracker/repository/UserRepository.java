package com.example.ExpenseTracker.repository;

import com.example.ExpenseTracker.model.UserDetails;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {
    // UserDetails findByEmail(String email);
    Optional<UserDetails> findByEmail(String email);

    @Query("SELECT u.id FROM UserDetails u")
    List<Long> findAllUserIds();

}

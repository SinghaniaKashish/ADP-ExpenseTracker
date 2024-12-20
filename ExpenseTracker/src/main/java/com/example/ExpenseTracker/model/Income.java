package com.example.ExpenseTracker.model;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    private double amount;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private IncomeCategory category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetails user;  // Link to User
    private LocalDateTime timeStamp;  // Add this field

}

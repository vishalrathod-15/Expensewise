package com.expensewise.expensewise.entity;

import com.expensewise.expensewise.dto.UserResponseDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    public UserResponseDTO toResponseDTO() {
        return new UserResponseDTO(this.id, this.name, this.email);
    }
}
package com.ecommerce.auth.infrastructure.driver_adapters.jpa_repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class UsuarioData {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String nombre;
        private String mail;
        @Column(length = 20, nullable = false)
        private String password;
        private String role;
        private Integer edad;

}

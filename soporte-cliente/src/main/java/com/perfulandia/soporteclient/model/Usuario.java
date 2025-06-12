package com.perfulandia.soporteclient.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    private String email;

    private String contrasena;

    private String rol;

    private String estado;
}

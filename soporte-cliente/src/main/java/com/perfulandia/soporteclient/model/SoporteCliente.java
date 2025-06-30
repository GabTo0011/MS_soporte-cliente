package com.perfulandia.soporteclient.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "soporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoporteCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "tipo_ticket")
    private String tipoTicket;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String estado;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "fecha_resolucion")
    private Date fechaResolucion;
}

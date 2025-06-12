package com.perfulandia.soporteclient.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SoporteClienteDTO {
    private Integer id;
    private Integer idUsuario;
    private String tipoTicket;
    private String descripcion;
    private String estado;
    private Date fechaCreacion;
    private Date fechaResolucion;
}

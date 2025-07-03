package com.perfulandia.soporteclient.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Data
public class SoporteClienteDTO extends RepresentationModel<SoporteClienteDTO> {
    private Integer id;
    private Integer idUsuario;
    private String tipoTicket;
    private String descripcion;
    private String estado;
    private Date fechaCreacion;
    private Date fechaResolucion;
}

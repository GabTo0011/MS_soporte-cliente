package com.perfulandia.soporteclient.service;

import com.perfulandia.soporteclient.dto.SoporteClienteDTO;
import com.perfulandia.soporteclient.model.SoporteCliente;
import com.perfulandia.soporteclient.model.Usuario;
import com.perfulandia.soporteclient.repository.SoporteClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SoporteClienteService {

    private final SoporteClienteRepository soporteClienteRepository;

    // Obtener todos los soportes
    public List<SoporteClienteDTO> obtenerTodos() {
        return soporteClienteRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Guardar un soporte
    public SoporteClienteDTO guardar(SoporteClienteDTO dto) {
        SoporteCliente soporte = convertirAEntidad(dto);
        soporte = soporteClienteRepository.save(soporte);
        return convertirADTO(soporte);
    }

    // Obtener soporte por ID
    public SoporteClienteDTO obtenerPorId(Integer id) {
        Optional<SoporteCliente> soporte = soporteClienteRepository.findById(id);
        return soporte.map(this::convertirADTO).orElse(null);
    }

    // Eliminar soporte por ID
    public void eliminar(Integer id) {
        soporteClienteRepository.deleteById(id);
    }

    // Convertir a DTO
    private SoporteClienteDTO convertirADTO(SoporteCliente soporte) {
        SoporteClienteDTO dto = new SoporteClienteDTO();
        dto.setId(soporte.getId());
        dto.setIdUsuario(soporte.getUsuario().getId());
        dto.setTipoTicket(soporte.getTipoTicket());
        dto.setDescripcion(soporte.getDescripcion());
        dto.setEstado(soporte.getEstado());
        dto.setFechaCreacion(soporte.getFechaCreacion());
        dto.setFechaResolucion(soporte.getFechaResolucion());
        return dto;
    }

    // Convertir de DTO a entidad
    private SoporteCliente convertirAEntidad(SoporteClienteDTO dto) {
        SoporteCliente soporte = new SoporteCliente();
        soporte.setId(dto.getId());

        Usuario usuario = new Usuario();
        usuario.setId(dto.getIdUsuario());
        soporte.setUsuario(usuario);

        soporte.setTipoTicket(dto.getTipoTicket());
        soporte.setDescripcion(dto.getDescripcion());
        soporte.setEstado(dto.getEstado());
        soporte.setFechaCreacion(dto.getFechaCreacion());
        soporte.setFechaResolucion(dto.getFechaResolucion());

        return soporte;
    }
}

package com.perfulandia.soporteclient.controller;

import com.perfulandia.soporteclient.dto.SoporteClienteDTO;
import com.perfulandia.soporteclient.service.SoporteClienteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/soporte")
@RequiredArgsConstructor
public class SoporteClienteController {

    private final SoporteClienteService soporteClienteService;

    @GetMapping
    @Operation(summary = "Obtiene todos los soportes", description = "Retorna todos los soportes en la base de datos")
    public List<SoporteClienteDTO> obtenerTodos() {
        List<SoporteClienteDTO> lista = soporteClienteService.obtenerTodos();

        for (SoporteClienteDTO dto : lista) {
            dto.add(linkTo(methodOn(SoporteClienteController.class).obtenerPorId(dto.getId())).withSelfRel());
            dto.add(linkTo(methodOn(SoporteClienteController.class).obtenerTodos()).withRel("todos"));
        }

        return lista;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un soporte por ID", description = "Retorna un soporte específico por su ID")
    public SoporteClienteDTO obtenerPorId(@PathVariable Integer id) {
        SoporteClienteDTO dto = soporteClienteService.obtenerPorId(id);

        dto.add(linkTo(methodOn(SoporteClienteController.class).obtenerPorId(id)).withSelfRel());
        dto.add(linkTo(methodOn(SoporteClienteController.class).obtenerTodos()).withRel("todos"));

        return dto;
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo soporte", description = "Guarda un nuevo soporte en la base de datos")
    public SoporteClienteDTO guardar(@RequestBody SoporteClienteDTO dto) {
        SoporteClienteDTO savedDTO = soporteClienteService.guardar(dto);

        savedDTO.add(linkTo(methodOn(SoporteClienteController.class).obtenerPorId(savedDTO.getId())).withSelfRel());
        return savedDTO;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un soporte", description = "Elimina un soporte existente por su ID")
    public void eliminar(@PathVariable Integer id) {
        soporteClienteService.eliminar(id);
    }
}

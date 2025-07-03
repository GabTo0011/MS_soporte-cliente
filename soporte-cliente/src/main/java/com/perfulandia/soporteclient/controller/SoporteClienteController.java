package com.perfulandia.soporteclient.controller;

import com.perfulandia.soporteclient.dto.SoporteClienteDTO;
import com.perfulandia.soporteclient.service.SoporteClienteService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/soporte")
@RequiredArgsConstructor
public class SoporteClienteController {

    private final SoporteClienteService soporteClienteService;

    @GetMapping
    @ApiOperation(value = "Obtiene todos los soportes", response = SoporteClienteDTO.class, responseContainer = "List")
    public List<SoporteClienteDTO> obtenerTodos() {
        List<SoporteClienteDTO> lista = soporteClienteService.obtenerTodos();
        
        // Agregamos enlaces HATEOAS
        for (SoporteClienteDTO dto : lista) {
            dto.add(linkTo(methodOn(SoporteClienteController.class).obtenerPorId(dto.getId())).withSelfRel());
            dto.add(linkTo(methodOn(SoporteClienteController.class).obtenerTodos()).withRel("todos"));
        }
        return lista;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un soporte cliente por su ID", response = SoporteClienteDTO.class)
    public SoporteClienteDTO obtenerPorId(@PathVariable Integer id) {
        SoporteClienteDTO dto = soporteClienteService.obtenerPorId(id);
        
        // Agregamos enlaces HATEOAS
        dto.add(linkTo(methodOn(SoporteClienteController.class).obtenerPorId(id)).withSelfRel());
        dto.add(linkTo(methodOn(SoporteClienteController.class).obtenerTodos()).withRel("todos"));
        
        return dto;
    }

    @PostMapping
    @ApiOperation(value = "Crea un nuevo soporte cliente", response = SoporteClienteDTO.class)
    public SoporteClienteDTO guardar(@RequestBody SoporteClienteDTO dto) {
        SoporteClienteDTO savedDTO = soporteClienteService.guardar(dto);
        
        // Agregamos enlaces HATEOAS al objeto guardado
        savedDTO.add(linkTo(methodOn(SoporteClienteController.class).obtenerPorId(savedDTO.getId())).withSelfRel());
        return savedDTO;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina un soporte cliente por su ID")
    public void eliminar(@PathVariable Integer id) {
        soporteClienteService.eliminar(id);
    }
}


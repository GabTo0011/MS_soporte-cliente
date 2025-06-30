package com.perfulandia.soporteclient.controller;

import com.perfulandia.soporteclient.dto.SoporteClienteDTO;
import com.perfulandia.soporteclient.service.SoporteClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/soporte")
@RequiredArgsConstructor
public class SoporteClienteController {

    private final SoporteClienteService soporteClienteService;

    @GetMapping
    public List<SoporteClienteDTO> obtenerTodos() {
        return soporteClienteService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public SoporteClienteDTO obtenerPorId(@PathVariable Integer id) {
        return soporteClienteService.obtenerPorId(id);
    }

    @PostMapping
    public SoporteClienteDTO guardar(@RequestBody SoporteClienteDTO dto) {
        return soporteClienteService.guardar(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        soporteClienteService.eliminar(id);
    }
}

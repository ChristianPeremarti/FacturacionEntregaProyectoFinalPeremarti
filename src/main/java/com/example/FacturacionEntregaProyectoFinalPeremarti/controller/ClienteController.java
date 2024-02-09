package com.example.FacturacionEntregaProyectoFinalPeremarti.controller;

import com.example.FacturacionEntregaProyectoFinalPeremarti.models.Cliente;
import com.example.FacturacionEntregaProyectoFinalPeremarti.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> findAll() {
        return this.clienteService.findAll();
    }

    // Single item

    @GetMapping("/{id}")
    public Cliente one(@PathVariable Integer id) {

        return this.clienteService.findById(id);
    }

    @PostMapping
    public Cliente newEntity(@RequestBody Cliente cliente) {
        return this.clienteService.save(cliente);
    }
}

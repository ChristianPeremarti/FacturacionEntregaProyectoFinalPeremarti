package com.example.FacturacionEntregaProyectoFinalPeremarti.controller;

import com.example.FacturacionEntregaProyectoFinalPeremarti.models.Comprobante;
import com.example.FacturacionEntregaProyectoFinalPeremarti.service.ComprobanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/comprobante")
public class ComprobanteController {

    @Autowired
    private ComprobanteService comprobanteService;

    @GetMapping
    public List<Comprobante> findAll() {
        return this.comprobanteService.findAll();
    }

    // Single item

    @GetMapping("/{id}")
    public Comprobante one(@PathVariable Integer id) {

        return this.comprobanteService.findById(id);
    }

    @PostMapping
    public Comprobante newEntity(@RequestBody Comprobante comprobante) {
        return this.comprobanteService.save(comprobante);
    }
}

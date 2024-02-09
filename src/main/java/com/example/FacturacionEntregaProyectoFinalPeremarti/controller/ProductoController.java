package com.example.FacturacionEntregaProyectoFinalPeremarti.controller;

import com.example.FacturacionEntregaProyectoFinalPeremarti.models.Producto;
import com.example.FacturacionEntregaProyectoFinalPeremarti.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> findAll() {
        return this.productoService.findAll();
    }

    // Single item

    @GetMapping("/{id}")
    public Producto one(@PathVariable Integer id) {

        return this.productoService.findById(id);
    }

    @PostMapping
    public Producto newEntity(@RequestBody Producto producto) {
        return this.productoService.save(producto);
    }
}

package com.example.FacturacionEntregaProyectoFinalPeremarti.service;

import com.example.FacturacionEntregaProyectoFinalPeremarti.models.Producto;
import com.example.FacturacionEntregaProyectoFinalPeremarti.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return this.productoRepository.findAll();
    }

    public Producto save(Producto cliente) {
        return this.productoRepository.save(cliente);
    }

    public Producto findById(Integer id) {

        var opCliente =  this.productoRepository.findById(id);
        if (opCliente.isPresent()) {
            return opCliente.get();
        } else {
            return new Producto();
        }
    }
}

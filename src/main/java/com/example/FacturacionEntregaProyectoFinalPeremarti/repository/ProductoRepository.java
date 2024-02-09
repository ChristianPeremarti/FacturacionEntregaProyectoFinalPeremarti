package com.example.FacturacionEntregaProyectoFinalPeremarti.repository;

import com.example.FacturacionEntregaProyectoFinalPeremarti.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}

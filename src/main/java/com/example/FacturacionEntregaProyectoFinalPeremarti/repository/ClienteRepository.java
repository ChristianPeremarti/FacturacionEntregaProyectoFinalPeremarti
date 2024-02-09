package com.example.FacturacionEntregaProyectoFinalPeremarti.repository;

import com.example.FacturacionEntregaProyectoFinalPeremarti.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


}

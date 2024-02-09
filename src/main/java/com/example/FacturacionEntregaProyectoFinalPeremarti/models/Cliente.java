package com.example.FacturacionEntregaProyectoFinalPeremarti.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="cliente")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer clienteid;

    @Column
    @Getter
    @Setter
    private String apellido;

    @Column
    @Getter
    @Setter
    private Integer dni;

    @Column
    @Getter
    @Setter
    private String nombre;

    public Cliente() {
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Cliente [");
        if (clienteid != null)
            builder.append("clienteid=").append(clienteid).append(", ");
        if (dni != null)
            builder.append("dni=").append(dni);
        builder.append("]");
        return builder.toString();
    }
}

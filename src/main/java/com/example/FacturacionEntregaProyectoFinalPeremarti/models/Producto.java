package com.example.FacturacionEntregaProyectoFinalPeremarti.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="producto")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer productoid;

    @Column
    @Getter
    @Setter
    private Integer cantidad;

    @Column
    @Getter
    @Setter
    private Integer codigo;

    @Column
    @Getter
    @Setter
    private String descripcion;

    @Column
    @Getter
    @Setter
    private BigDecimal precio;

    public Producto() {
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Producto [");
        if (productoid != null)
            builder.append("productoid=").append(productoid).append(", ");
        if (cantidad != null)
            builder.append("cantidad=").append(cantidad).append(", ");
        if (codigo != null)
            builder.append("codigo=").append(codigo);
        builder.append("]");
        return builder.toString();
    }
}

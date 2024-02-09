package com.example.FacturacionEntregaProyectoFinalPeremarti.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="linea")
@NamedQuery(name="Linea.findAll", query="SELECT l FROM Linea l")
public class Linea implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer lineaid;

    @Column
    @Getter
    @Setter
    private Integer cantidad;

    @Column
    @Getter
    @Setter
    private String descripcion;

    @Column
    @Getter
    @Setter
    private BigDecimal precio;

    //bi-directional many-to-one association to Comprobante
    @ManyToOne
    @JoinColumn(name="comprobanteid")
    @Getter
    @Setter
    private Comprobante comprobante;

    //bi-directional many-to-one association to Producto
    @ManyToOne
    @JoinColumn(name="productoid")
    @Getter
    @Setter
    private Producto producto;

    public Linea() {
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Linea [lineaid=").append(lineaid).append(", cantidad=").append(cantidad).append(", ");
        if (descripcion != null)
            builder.append("descripcion=").append(descripcion).append(", ");
        if (precio != null)
            builder.append("precio=").append(precio);
        builder.append("]");
        return builder.toString();
    }
}

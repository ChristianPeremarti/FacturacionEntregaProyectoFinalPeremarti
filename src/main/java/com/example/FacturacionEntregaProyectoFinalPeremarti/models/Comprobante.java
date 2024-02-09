package com.example.FacturacionEntregaProyectoFinalPeremarti.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="comprobante")
@NamedQuery(name="Comprobante.findAll", query="SELECT c FROM Comprobante c")
public class Comprobante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer comprobanteid;

    @Column
    @Getter
    @Setter
    private Integer cantidad;

    @Column
    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column
    @Getter
    @Setter
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name="clienteid")
    @Getter
    @Setter
    private Cliente cliente;

    @Getter
    @Setter
    @OneToMany(mappedBy="comprobante", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Linea> lineas;

    public Comprobante() {
    }

    public Linea addLinea(Linea linea) {
        getLineas().add(linea);
        linea.setComprobante(this);

        return linea;
    }

    public Linea removeLinea(Linea linea) {
        getLineas().remove(linea);
        linea.setComprobante(null);

        return linea;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Comprobante [");
        if (comprobanteid != null)
            builder.append("comprobanteid=").append(comprobanteid).append(", ");
        if (cantidad != null)
            builder.append("cantidad=").append(cantidad).append(", ");
        if (fecha != null)
            builder.append("fecha=").append(fecha).append(", ");
        if (total != null)
            builder.append("total=").append(total).append(", ");
        if (cliente != null)
            builder.append("cliente=").append(cliente).append(", ");
        if (lineas != null)
            builder.append("lineas=").append(lineas);
        builder.append("]");
        return builder.toString();
    }
}

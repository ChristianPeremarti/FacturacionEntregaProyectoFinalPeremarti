package com.example.FacturacionEntregaProyectoFinalPeremarti.service;

import com.example.FacturacionEntregaProyectoFinalPeremarti.models.*;
import com.example.FacturacionEntregaProyectoFinalPeremarti.repository.ClienteRepository;
import com.example.FacturacionEntregaProyectoFinalPeremarti.repository.ComprobanteRepository;
import com.example.FacturacionEntregaProyectoFinalPeremarti.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ComprobanteService {

    @Autowired
    private ComprobanteRepository comprobanteRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Comprobante> findAll() {
        return crearComprobantesDTO(this.comprobanteRepository.findAll());
    }

    public Comprobante save(Comprobante comprobante) {

        Boolean existeCliente = existeCliente(comprobante.getCliente());

        Boolean existenProductos = existenProductos(comprobante.getLineas());

        Boolean existeStock = existeStock(comprobante.getLineas());

        if (existeCliente && existenProductos && existeStock) {

            var comprobanteAGuardar = armarComprobante(comprobante);

            actualizarStock(comprobanteAGuardar.getLineas());

            return crearComprobanteDTO(this.comprobanteRepository.save(comprobanteAGuardar));
        } else {
            return new Comprobante();
        }
    }

    private void actualizarStock(Set<Linea> lineas) {
        for (Linea linea : lineas) {

            var cantidadVendida = linea.getCantidad();
            var producto = linea.getProducto();

            var productoDB = this.productoRepository.getById(producto.getProductoid());
            var stock = productoDB.getCantidad();
            var nuevoStock = stock - cantidadVendida;
            productoDB.setCantidad(nuevoStock);

            this.productoRepository.save(productoDB);

        }

    }

    public Comprobante findById(Integer id) {

        var opCliente =  this.comprobanteRepository.findById(id);
        if (opCliente.isPresent()) {
            return crearComprobanteDTO(opCliente.get());
        } else {
            return new Comprobante();
        }
    }

    private List<Comprobante> crearComprobantesDTO(List<Comprobante> comprobantes) {
        List<Comprobante> comprobantesDTOs = new ArrayList<Comprobante>();
        for (Comprobante comprobante : comprobantes) {
            comprobantesDTOs.add(this.crearComprobanteDTO(comprobante));
        }

        return comprobantesDTOs;
    }

    private Comprobante crearComprobanteDTO(Comprobante comprobante) {
        Comprobante dto = new Comprobante();

        dto.setComprobanteid(comprobante.getComprobanteid());

        dto.setCantidad(comprobante.getCantidad());

        dto.setFecha(comprobante.getFecha());

        dto.setTotal(comprobante.getTotal());

        dto.setCliente(comprobante.getCliente());

        dto.setLineas(crearLineasDTO(comprobante.getLineas()));


        return dto;
    }

    private Set<Linea> crearLineasDTO(Set<Linea> lineas) {
        Set<Linea> dtos = new HashSet<Linea>();

        for (Linea linea : lineas) {

            Linea dto = new Linea();

            dto.setLineaid(linea.getLineaid());

            dto.setCantidad(linea.getCantidad());

            dto.setDescripcion(linea.getDescripcion());

            dto.setPrecio(linea.getPrecio());

            dtos.add(dto);

        }

        return dtos;
    }

    private Comprobante armarComprobante(Comprobante comprobante) {
        var comprobanteAGuardar = new Comprobante();

        comprobanteAGuardar.setCliente(this.clienteRepository.findById(comprobante.getCliente().getClienteid()).get());

        WorldClock worldClock = this.restTemplate.getForObject("http://worldclockapi.com/api/json/utc/now", WorldClock.class);

        String currentDateTime = worldClock.getCurrentDateTime();
        // "2021-12-08T17:36Z"
        try {
            Date date1=new SimpleDateFormat("yyyy-MM-dd'T'mm:ss'Z'").parse(currentDateTime);
            comprobanteAGuardar.setFecha(date1);
        } catch (ParseException e) {
            e.printStackTrace();
            comprobanteAGuardar.setFecha(new Date());
        }

        comprobanteAGuardar.setLineas(new HashSet<Linea>());
        for (Linea linea : comprobante.getLineas()) {
            comprobanteAGuardar.addLinea(crearLinea(linea));
        }


        comprobanteAGuardar.setTotal(calcularTotal(comprobanteAGuardar.getLineas()));
        comprobanteAGuardar.setCantidad(comprobante.getLineas().size());

        return comprobanteAGuardar;
    }

    private BigDecimal calcularTotal(Set<Linea> lineas) {
        BigDecimal total = new BigDecimal("0");

        for (Linea linea : lineas) {
            total = total.add(new BigDecimal(linea.getPrecio().toString()));
        }

        return total;
    }

    private Linea crearLinea(Linea linea) {
        Linea lineaAGuardar = new Linea();

        Producto productoDB = this.productoRepository.findById(linea.getProducto().getProductoid()).get();
        lineaAGuardar.setCantidad(linea.getCantidad());
        lineaAGuardar.setDescripcion(productoDB.getDescripcion());
        lineaAGuardar.setPrecio(productoDB.getPrecio());
        lineaAGuardar.setProducto(productoDB);

        return lineaAGuardar;
    }

    private Boolean existeStock(Set<Linea> lineas) {
        for (Linea linea : lineas) {
            var productoid = linea.getProducto().getProductoid();
            var opProducto = this.productoRepository.findById(productoid);
            if (opProducto.isEmpty()) {
                return false;
            }
            if (linea.getCantidad() < opProducto.get().getCantidad()) {
                return true;
            }
        }
        return false;
    }

    private Boolean existenProductos(Set<Linea> lineas) {
        for (Linea linea : lineas) {
            var productoId = linea.getProducto().getProductoid();
            var opProducto = this.productoRepository.findById(productoId);
            if (opProducto.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private Boolean existeCliente(Cliente cliente) {
        var opCliente = this.clienteRepository.findById(cliente.getClienteid());
        return !opCliente.isEmpty();
    }
}

package com.utn.Trabajo_Practico_N1.entidades;


import com.utn.Trabajo_Practico_N1.Enums.Estado;
import com.utn.Trabajo_Practico_N1.Enums.TipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido extends BaseEntidad {
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Enumerated(EnumType.STRING)
    private TipoEnvio tipoEnvio;
    private double total;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "IdFactura", nullable = true)
    private Factura factura;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "Pedido_id")
    @Builder.Default
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    public void agregarDetallePedido(DetallePedido detPedido){
        detallePedidos.add(detPedido);
    }
    public void mostrarfactura(){
        System.out.println("Factura del pedido: " + factura.getNumero());
            System.out.println("Forma de pago: " + factura.getFormaPago());
            System.out.println("Descuento: " + factura.getDescuento());
            System.out.println("Fecha: " + factura.getFecha());
            System.out.println("Total: " + factura.getTotal());

        }
    }
package com.utn.Trabajo_Practico_N1.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rubro extends BaseEntidad{
    private String denominacion;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "Rubro_id")
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto produ){

        productos.add(produ);
    }
    public void mostrarProductos(){
        System.out.println("Productos del Rubro " + denominacion + " : ");
        for (Producto producto : productos){
            System.out.println("Tipo: "+ producto.getTipo() + ", Denominacion: "+ producto.getDenominacion() + ", tiempo de cocina " +
                    producto.getTiempoEstimadoCocina()+ ", precio: " + producto.getPrecioVenta() + ", precio a comprar: " + producto.getPrecioCompra()+
                    ", stock: " + producto.getStockActual() + ", stock minimo: " + producto.getStockMínimo() + ", unidad de Medida: " +
                    producto.getUnidadMedida() + " y recta: " + producto.getReceta());
        }
    }
}

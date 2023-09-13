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
public class Cliente extends BaseEntidad{
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "Cliente_id")
    @Builder.Default
    private List<Domicilio> domicilios = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "Cliente_id")
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();
    public void agregarDomicilio(Domicilio domi){

        domicilios.add(domi);
    }
    public void mostrarDomicilio(){
        System.out.println("Domicilios de " + nombre +" "+ apellido + " : ");
        for (Domicilio domicilio : domicilios){
            System.out.println("Calle " + domicilio.getCalle()+ ", Numero "+ domicilio.getNumero()+ " y Localidad "+ domicilio.getLocalidad() );
        }
    }
    public void agregarPedido(Pedido pedi){
        pedidos.add(pedi);
    }
    public void mostrarPedido() {
        System.out.println("Pedidos de " + nombre + " " + apellido + ": ");
        for (Pedido pedido : pedidos) {
            System.out.println("Estado: " + pedido.getEstado() + ", fecha: " + pedido.getFecha() + ", envio: " + pedido.getTipoEnvio() + " y total: " + pedido.getTotal());

        }
    }
}
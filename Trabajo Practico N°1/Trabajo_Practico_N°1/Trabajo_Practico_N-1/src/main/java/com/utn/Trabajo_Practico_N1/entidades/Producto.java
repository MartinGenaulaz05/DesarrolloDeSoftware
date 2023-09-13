package com.utn.Trabajo_Practico_N1.entidades;

import com.utn.Trabajo_Practico_N1.Enums.Tipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto extends BaseEntidad {
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    private int tiempoEstimadoCocina;
    private String denominacion;
    private double precioVenta;
    private double precioCompra;
    private int stockActual;
    private int stockMínimo;
    private String unidadMedida;
    private String receta;

}

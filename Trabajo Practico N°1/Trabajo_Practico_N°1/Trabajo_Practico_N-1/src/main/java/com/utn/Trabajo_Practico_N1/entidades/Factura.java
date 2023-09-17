package com.utn.Trabajo_Practico_N1.entidades;

import com.utn.Trabajo_Practico_N1.Enums.FormaPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Factura extends BaseEntidad {
    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;
    private int numero;
    private Date fecha;
    private double descuento;
    private int total;
}

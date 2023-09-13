package com.utn.Trabajo_Practico_N1.repositorios;

import com.utn.Trabajo_Practico_N1.entidades.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido,Long> {
}

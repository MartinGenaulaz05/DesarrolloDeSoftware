package com.utn.Trabajo_Practico_N1.repositorios;

import com.utn.Trabajo_Practico_N1.entidades.Rubro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubroRepository extends JpaRepository<Rubro,Long> {
}

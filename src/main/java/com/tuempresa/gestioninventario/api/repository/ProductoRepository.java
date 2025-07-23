package com.tuempresa.gestioninventario.api.repository;

import com.tuempresa.gestioninventario.api.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}

package com.ejemplo.proyecto.repository;

import com.ejemplo.proyecto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}

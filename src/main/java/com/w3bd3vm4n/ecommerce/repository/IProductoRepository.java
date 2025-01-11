package com.w3bd3vm4n.ecommerce.repository;

import com.w3bd3vm4n.ecommerce.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto, Long> {
}

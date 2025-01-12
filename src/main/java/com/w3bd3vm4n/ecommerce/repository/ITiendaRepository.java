package com.w3bd3vm4n.ecommerce.repository;

import com.w3bd3vm4n.ecommerce.model.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITiendaRepository extends JpaRepository<Tienda, Long> {
}

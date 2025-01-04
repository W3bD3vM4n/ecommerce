package com.w3bd3vm4n.ecommerce.repository;

import com.w3bd3vm4n.ecommerce.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * IClienteRepository cierra la brecha entre la Base de Datos
 * y la "Lógica de Negocio" implementada en el ClienteService
 */
public interface IClienteRepository extends JpaRepository<Cliente, Long> {
}

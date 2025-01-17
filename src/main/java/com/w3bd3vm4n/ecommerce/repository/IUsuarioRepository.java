package com.w3bd3vm4n.ecommerce.repository;

import com.w3bd3vm4n.ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
}

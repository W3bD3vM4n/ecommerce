package com.w3bd3vm4n.ecommerce.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UsuarioResponseDTO {

    private Long id;
    private String nombre;
    private String contrasena;
    private boolean habilitado;
    private Set<String> rol;

}

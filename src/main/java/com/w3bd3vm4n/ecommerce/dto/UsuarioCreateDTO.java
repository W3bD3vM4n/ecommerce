package com.w3bd3vm4n.ecommerce.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UsuarioCreateDTO {

    private String nombre;
    private String contrasena;
    private Boolean habilitado;
    private Set<Long> rol;

}

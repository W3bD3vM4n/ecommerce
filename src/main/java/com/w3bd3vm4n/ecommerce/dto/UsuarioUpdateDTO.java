package com.w3bd3vm4n.ecommerce.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UsuarioUpdateDTO {

    private String contrasena;
    private Boolean habilitado;
    private Set<Long> rol;

}

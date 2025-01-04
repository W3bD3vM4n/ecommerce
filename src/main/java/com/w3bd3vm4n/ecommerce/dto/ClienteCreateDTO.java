package com.w3bd3vm4n.ecommerce.dto;

import lombok.Data;

@Data
public class ClienteCreateDTO {

    private String nombre;
    private String apellido;
    private String email;
    private String celular;
    private String direccion;

}

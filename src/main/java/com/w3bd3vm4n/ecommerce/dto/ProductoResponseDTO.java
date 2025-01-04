package com.w3bd3vm4n.ecommerce.dto;

import lombok.Data;

@Data
public class ProductoResponseDTO {

    private Long id;
    private String titulo;
    private String detalle;
    private Double precio;

}

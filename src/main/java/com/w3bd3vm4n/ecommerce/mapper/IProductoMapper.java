package com.w3bd3vm4n.ecommerce.mapper;

import com.w3bd3vm4n.ecommerce.dto.ProductoCreateDTO;
import com.w3bd3vm4n.ecommerce.dto.ProductoResponseDTO;
import com.w3bd3vm4n.ecommerce.model.Producto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel =  "spring")
public interface IProductoMapper {

    ProductoResponseDTO mapearAProductoResponseDTO(Producto producto);

    List<ProductoResponseDTO> mapearAListaProductoResponseDTO(List<Producto> productos);

    Producto mapearAProducto(ProductoCreateDTO dto);

}

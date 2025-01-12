package com.w3bd3vm4n.ecommerce.mapper;

import com.w3bd3vm4n.ecommerce.dto.TiendaCreateDTO;
import com.w3bd3vm4n.ecommerce.dto.TiendaResponseDTO;
import com.w3bd3vm4n.ecommerce.model.Tienda;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITiendaMapper {

    TiendaResponseDTO mapearATiendaResponseDTO(Tienda tienda);

    List<TiendaResponseDTO> mapearAListaTiendaResponseDTO(List<Tienda> tiendas);

    Tienda mapearATienda(TiendaCreateDTO dto);

}

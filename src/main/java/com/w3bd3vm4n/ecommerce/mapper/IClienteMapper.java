package com.w3bd3vm4n.ecommerce.mapper;

import com.w3bd3vm4n.ecommerce.dto.ClienteCreateDTO;
import com.w3bd3vm4n.ecommerce.dto.ClienteResponseDTO;
import com.w3bd3vm4n.ecommerce.model.Cliente;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * IClienteMapper es un asignador MapStruct que se utiliza para asignar diferentes
 * tipos de objetos en la aplicación
 */
@Mapper(componentModel = "spring")
public interface IClienteMapper {

    ClienteResponseDTO mapearAClienteResponseDTO(Cliente cliente);

    List<ClienteResponseDTO> mapearAListaClienteResponseDTO(List<Cliente> clientes);

    Cliente mapearACliente(ClienteCreateDTO dto);

}

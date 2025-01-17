package com.w3bd3vm4n.ecommerce.mapper;

import com.w3bd3vm4n.ecommerce.dto.UsuarioCreateDTO;
import com.w3bd3vm4n.ecommerce.dto.UsuarioResponseDTO;
import com.w3bd3vm4n.ecommerce.mapper.helper.RolMapperHelper;
import com.w3bd3vm4n.ecommerce.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = RolMapperHelper.class)
public interface IUsuarioMapper {

    @Mapping(target = "rol", source = "rol", qualifiedByName = "extraerNombresDeRol")
    UsuarioResponseDTO mapearAUsuarioResponseDTO(Usuario usuario);

    List<UsuarioResponseDTO> mapearAListaUsuarioResponseDTO(List<Usuario> usuarios);

    @Mapping(target = "rol", source = "rol", qualifiedByName = "mapearRolDeId")
    Usuario mapearAUsuario(UsuarioCreateDTO dto);

}

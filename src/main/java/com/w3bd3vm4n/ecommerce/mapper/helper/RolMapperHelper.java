package com.w3bd3vm4n.ecommerce.mapper.helper;

import com.w3bd3vm4n.ecommerce.model.Rol;
import com.w3bd3vm4n.ecommerce.repository.IRolRepository;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RolMapperHelper {

    @Autowired
    private IRolRepository iRolRepository;

    @Named("extraerNombresDeRol")
    public Set<String> extraerNombresDeRol(Set<Rol> roles) {
        return roles.stream()
                .map(Rol::getNombre).collect(Collectors.toSet());
    }

    @Named("mapearRolDeId")
    public Set<Rol> mapearRolDeId(Set<Long> rolId) {
        return rolId.stream()
                .map(iRolRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

}

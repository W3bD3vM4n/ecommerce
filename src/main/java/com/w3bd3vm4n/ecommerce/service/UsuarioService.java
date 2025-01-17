package com.w3bd3vm4n.ecommerce.service;

import com.w3bd3vm4n.ecommerce.model.Usuario;
import com.w3bd3vm4n.ecommerce.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    public List<Usuario> obtenerListaUsuariosDesdeRepositorio() {
        return iUsuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorIdDesdeRepositorio(Long id) {
        return iUsuarioRepository.findById(id);
    }

    public Usuario guardarUsuarioDesdeRepositorio(Usuario usuario) {
        if (usuario.getId() != null && !iUsuarioRepository.existsById(usuario.getId())) {
            throw new IllegalArgumentException("Usuario con ID" + usuario.getId() + " no existe");
        }
        return iUsuarioRepository.save(usuario);
    }

    public void borrarUsuarioDesdeRepositorio(Long id) {
        iUsuarioRepository.deleteById(id);
    }

}

package com.w3bd3vm4n.ecommerce.controller;

import com.w3bd3vm4n.ecommerce.dto.UsuarioCreateDTO;
import com.w3bd3vm4n.ecommerce.dto.UsuarioResponseDTO;
import com.w3bd3vm4n.ecommerce.mapper.IUsuarioMapper;
import com.w3bd3vm4n.ecommerce.model.Usuario;
import com.w3bd3vm4n.ecommerce.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // habilita log.info
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private IUsuarioMapper iUsuarioMapper;

    @GetMapping
    public List<UsuarioResponseDTO> obtenerTodosLosUsuarios() {
//        List<Usuario> usuarios = usuarioService.obtenerListaUsuariosDesdeRepositorio();
//        log.info("Fetched Usuarios: {}", usuarios);
        return iUsuarioMapper.mapearAListaUsuarioResponseDTO(usuarioService.obtenerListaUsuariosDesdeRepositorio());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorIdDesdeRepositorio(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crearUsuario(@RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        if (usuarioCreateDTO.getNombre() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Usuario usuario = iUsuarioMapper.mapearAUsuario(usuarioCreateDTO);
        Usuario guardarUsuario = usuarioService.guardarUsuarioDesdeRepositorio(usuario);
        UsuarioResponseDTO responseDTO = iUsuarioMapper.mapearAUsuarioResponseDTO(guardarUsuario);

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        return usuarioService.obtenerUsuarioPorIdDesdeRepositorio(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setNombre(usuarioCreateDTO.getNombre());
                    usuarioExistente.setContrasena(usuarioCreateDTO.getContrasena());
                    usuarioExistente.setHabilitado(usuarioCreateDTO.getHabilitado());

                    Usuario actualizarUsuario = usuarioService.guardarUsuarioDesdeRepositorio(usuarioExistente);
                    UsuarioResponseDTO responseDTO = iUsuarioMapper.mapearAUsuarioResponseDTO(actualizarUsuario);

                    return ResponseEntity.ok(responseDTO);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarUsuario(@PathVariable Long id) {
        usuarioService.borrarUsuarioDesdeRepositorio(id);
        return ResponseEntity.noContent().build();
    }

}

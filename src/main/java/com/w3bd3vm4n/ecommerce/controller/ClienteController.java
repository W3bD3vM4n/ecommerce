package com.w3bd3vm4n.ecommerce.controller;

import com.w3bd3vm4n.ecommerce.dto.ClienteCreateDTO;
import com.w3bd3vm4n.ecommerce.dto.ClienteResponseDTO;
import com.w3bd3vm4n.ecommerce.mapper.IClienteMapper;
import com.w3bd3vm4n.ecommerce.model.Cliente;
import com.w3bd3vm4n.ecommerce.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private IClienteMapper iClienteMapper;

    @GetMapping
    public List<ClienteResponseDTO> obtenerTodosLosClientes() {
        return iClienteMapper.mapearAListaClienteResponseDTO(clienteService.obtenerListaClientesDesdeRepositorio());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        return clienteService.obtenerClientePorIdDesdeRepositorio(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crearCliente(@RequestBody ClienteCreateDTO clienteCreateDTO) {
        if (clienteCreateDTO.getNombre() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Cliente cliente = iClienteMapper.mapearACliente(clienteCreateDTO);
        Cliente guardarCliente = clienteService.guardarClienteDesdeRepositorio(cliente);
        ClienteResponseDTO responseDTO = iClienteMapper.mapearAClienteResponseDTO(guardarCliente);

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> actualizarCliente(@PathVariable Long id, @RequestBody ClienteCreateDTO clienteCreateDTO) {
        return clienteService.obtenerClientePorIdDesdeRepositorio(id)
            .map(clienteExistente -> {
                clienteExistente.setNombre(clienteCreateDTO.getNombre());
                clienteExistente.setApellido(clienteCreateDTO.getApellido());
                clienteExistente.setEmail(clienteCreateDTO.getEmail());
                clienteExistente.setCelular(clienteCreateDTO.getCelular());
                clienteExistente.setDireccion(clienteCreateDTO.getDireccion());

                Cliente actualizarCliente = clienteService.guardarClienteDesdeRepositorio(clienteExistente);
                ClienteResponseDTO responseDTO = iClienteMapper.mapearAClienteResponseDTO(actualizarCliente);

                return ResponseEntity.ok(responseDTO);
            })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarCliente(@PathVariable Long id) {
        clienteService.borrarClienteDesdeRepositorio(id);
        return ResponseEntity.noContent().build();
    }

}

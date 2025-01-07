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
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private IClienteMapper iClienteMapper;

    @GetMapping
    public List<ClienteResponseDTO> obtenerTodosLosClientes() {
        return iClienteMapper.convertirListaClientes(clienteService.obtenerTodosLosClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        return clienteService.obtenerClientePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crearCliente(@RequestBody ClienteCreateDTO clienteCreateDTO) {
        if (clienteCreateDTO.getNombre() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Cliente cliente = iClienteMapper.convertirClienteCreateDTO(clienteCreateDTO);
        Cliente guardarCliente = clienteService.guardarCliente(cliente);
        ClienteResponseDTO responseDTO = iClienteMapper.convertirCliente(guardarCliente);

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> actualizarCliente(@PathVariable Long id, @RequestBody ClienteCreateDTO clienteCreateDTO) {
        return clienteService.obtenerClientePorId(id)
            .map(clienteExistente -> {
                    clienteExistente.setNombre(clienteCreateDTO.getNombre());
                    clienteExistente.setEmail(clienteCreateDTO.getEmail());

                    Cliente actualizarCliente = clienteService.guardarCliente(clienteExistente);
                    ClienteResponseDTO responseDTO = iClienteMapper.convertirCliente(actualizarCliente);

                    return ResponseEntity.ok(responseDTO);
            })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarCliente(@PathVariable Long id) {
        clienteService.borrarCliente(id);
        return  ResponseEntity.noContent().build();
    }

}

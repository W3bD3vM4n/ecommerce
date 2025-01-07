package com.w3bd3vm4n.ecommerce.service;

import com.w3bd3vm4n.ecommerce.model.Cliente;
import com.w3bd3vm4n.ecommerce.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * La clase ClienteService encapsula la "Lógica de Negocio" relacionada con el Cliente,
 * procesa y valida las operaciones delegando a IUserRepository antes de interactuar
 * con la Base de Datos
 */
@Service
public class ClienteService {

    @Autowired
    private IClienteRepository iClienteRepository;

    public List<Cliente> obtenerTodosLosClientes() {
        return iClienteRepository.findAll();
    }

    public Optional<Cliente> obtenerClientePorId(Long id) {
        return iClienteRepository.findById(id);
    }

    public Cliente guardarCliente(Cliente cliente) {
        if (cliente.getId() != null && !iClienteRepository.existsById(cliente.getId())) {
            throw new IllegalArgumentException("Cliente con ID" + cliente.getId() + " no existe");
        }
        return iClienteRepository.save(cliente);
    }

    public void borrarCliente(Long id) {
        iClienteRepository.deleteById(id);
    }

}

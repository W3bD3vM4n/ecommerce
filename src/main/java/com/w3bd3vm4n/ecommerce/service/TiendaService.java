package com.w3bd3vm4n.ecommerce.service;

import com.w3bd3vm4n.ecommerce.model.Tienda;
import com.w3bd3vm4n.ecommerce.repository.ITiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TiendaService {

    @Autowired
    private ITiendaRepository iTiendaRepository;

    public List<Tienda> obtenerListaTiendasDesdeRepositorio() {
        return iTiendaRepository.findAll();
    }

    public Optional<Tienda> obtenerTiendaPorIdDesdeRepositorio(Long id) {
        return iTiendaRepository.findById(id);
    }

    public Tienda guardarTiendaDesdeRepositorio(Tienda tienda) {
        if (tienda.getId() != null && !iTiendaRepository.existsById(tienda.getId())) {
            throw new IllegalArgumentException("Tienda con ID" + tienda.getId() + " no existe");
        }
        return iTiendaRepository.save(tienda);
    }

    public void borrarTiendaDesdeRepositorio(Long id) {
        iTiendaRepository.deleteById(id);
    }

}

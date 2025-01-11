package com.w3bd3vm4n.ecommerce.service;

import com.w3bd3vm4n.ecommerce.model.Producto;
import com.w3bd3vm4n.ecommerce.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private IProductoRepository iProductoRepository;

    public List<Producto> obtenerListaProductosDesdeRepositorio() {
        return iProductoRepository.findAll();
    }

    public Optional<Producto> obtenerProductoPorIdDesdeRepositorio(Long id) {
        return iProductoRepository.findById(id);
    }

    public Producto guardarProductoDesdeRepositorio(Producto producto) {
        if (producto.getId() != null && !iProductoRepository.existsById(producto.getId())) {
            throw new IllegalArgumentException("Producto con ID" + producto.getId() + " no existe");
        }
        return iProductoRepository.save(producto);
    }

    public void borrarProductoDesdeRepositorio(Long id) {
        iProductoRepository.deleteById(id);
    }

}

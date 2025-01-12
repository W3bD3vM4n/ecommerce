package com.w3bd3vm4n.ecommerce.controller;

import com.w3bd3vm4n.ecommerce.dto.ProductoCreateDTO;
import com.w3bd3vm4n.ecommerce.dto.ProductoResponseDTO;
import com.w3bd3vm4n.ecommerce.mapper.IProductoMapper;
import com.w3bd3vm4n.ecommerce.model.Producto;
import com.w3bd3vm4n.ecommerce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private IProductoMapper iProductoMapper;

    @GetMapping
    public List<ProductoResponseDTO> obtenerTodosLosProductos() {
        return iProductoMapper.mapearAListaProductoResponseDTO(productoService.obtenerListaProductosDesdeRepositorio());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        return productoService.obtenerProductoPorIdDesdeRepositorio(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(@RequestBody ProductoCreateDTO productoCreateDTO) {
        if (productoCreateDTO.getTitulo() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Producto producto = iProductoMapper.mapearAProducto(productoCreateDTO);
        Producto guardarProducto = productoService.guardarProductoDesdeRepositorio(producto);
        ProductoResponseDTO responseDTO = iProductoMapper.mapearAProductoResponseDTO(guardarProducto);

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(@PathVariable Long id, @RequestBody ProductoCreateDTO productoCreateDTO) {
        return productoService.obtenerProductoPorIdDesdeRepositorio(id)
                .map(productoExistente -> {
                    productoExistente.setTitulo(productoCreateDTO.getTitulo());
                    productoExistente.setDetalle(productoCreateDTO.getDetalle());
                    productoExistente.setPrecio(productoCreateDTO.getPrecio());

                    Producto actualizarProducto = productoService.guardarProductoDesdeRepositorio(productoExistente);
                    ProductoResponseDTO responseDTO = iProductoMapper.mapearAProductoResponseDTO(actualizarProducto);

                    return ResponseEntity.ok(responseDTO);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarProducto(@PathVariable Long id) {
        productoService.borrarProductoDesdeRepositorio(id);
        return ResponseEntity.noContent().build();
    }

}

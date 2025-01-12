package com.w3bd3vm4n.ecommerce.controller;

import com.w3bd3vm4n.ecommerce.dto.TiendaCreateDTO;
import com.w3bd3vm4n.ecommerce.dto.TiendaResponseDTO;
import com.w3bd3vm4n.ecommerce.mapper.ITiendaMapper;
import com.w3bd3vm4n.ecommerce.model.Tienda;
import com.w3bd3vm4n.ecommerce.service.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tienda")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;
    @Autowired
    private ITiendaMapper iTiendaMapper;

    @GetMapping
    public List<TiendaResponseDTO> obtenerTodasLasTiendas() {
        return iTiendaMapper.mapearAListaTiendaResponseDTO(tiendaService.obtenerListaTiendasDesdeRepositorio());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tienda> obtenerTiendaPorId(@PathVariable Long id) {
        return tiendaService.obtenerTiendaPorIdDesdeRepositorio(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TiendaResponseDTO> crearTienda(@RequestBody TiendaCreateDTO tiendaCreateDTO) {
        if (tiendaCreateDTO.getNombre() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Tienda tienda = iTiendaMapper.mapearATienda(tiendaCreateDTO);
        Tienda guardarTienda = tiendaService.guardarTiendaDesdeRepositorio(tienda);
        TiendaResponseDTO responseDTO = iTiendaMapper.mapearATiendaResponseDTO(guardarTienda);

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TiendaResponseDTO> actualizarTienda(@PathVariable Long id, @RequestBody TiendaCreateDTO tiendaCreateDTO) {
        return tiendaService.obtenerTiendaPorIdDesdeRepositorio(id)
                .map(tiendaExistente -> {
                    tiendaExistente.setNombre(tiendaCreateDTO.getNombre());
                    tiendaExistente.setRuc(tiendaCreateDTO.getRuc());
                    tiendaExistente.setDireccion(tiendaCreateDTO.getDireccion());
                    tiendaExistente.setTelefono(tiendaCreateDTO.getTelefono());

                    Tienda actualizarTienda = tiendaService.guardarTiendaDesdeRepositorio(tiendaExistente);
                    TiendaResponseDTO responseDTO = iTiendaMapper.mapearATiendaResponseDTO(actualizarTienda);

                    return ResponseEntity.ok(responseDTO);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarTienda(@PathVariable Long id) {
        tiendaService.borrarTiendaDesdeRepositorio(id);
        return ResponseEntity.noContent().build();
    }

}

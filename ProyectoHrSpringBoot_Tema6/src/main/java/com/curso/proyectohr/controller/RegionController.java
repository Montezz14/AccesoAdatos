package com.curso.proyectohr.controller;

import com.curso.proyectohr.dominio.Region;
import com.curso.proyectohr.servicios.RegionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/regions")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    // GET /api/regions
    // Obtiene todas las regiones
    @GetMapping
    public ResponseEntity<List<Region>> obtenerTodas() {
        List<Region> regiones = regionService.obtenerTodas();
        return ResponseEntity.ok(regiones);
    }

    // GET /api/regions/{id}
    // Obtiene una región por ID
    @GetMapping("/{id}")
    public ResponseEntity<Region> obtenerPorId(@PathVariable Long id) {
        return regionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /api/regions
    // Crea una nueva región
    @PostMapping
    public ResponseEntity<Region> crear(@RequestBody Region region) {
        Region guardada = regionService.crearRegion(region);
        return new ResponseEntity<>(guardada, HttpStatus.CREATED);
    }

    // DELETE /api/regions/{id}
    // Elimina una región
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        regionService.eliminarRegion(id);
        return ResponseEntity.noContent().build();
    }
}

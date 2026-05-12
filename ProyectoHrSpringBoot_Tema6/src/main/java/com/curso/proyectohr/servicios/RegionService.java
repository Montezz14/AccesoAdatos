package com.curso.proyectohr.servicios;

import com.curso.proyectohr.dominio.Region;
import com.curso.proyectohr.repositorios.RegionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Transactional(readOnly = true)
    public List<Region> obtenerTodas() {
        return regionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Region> obtenerPorId(Long id) {
        return regionRepository.findById(id);
    }

    @Transactional
    public Region crearRegion(Region region) {
        return regionRepository.save(region);
    }

    @Transactional
    public void eliminarRegion(Long id) {
        regionRepository.deleteById(id);
    }
}

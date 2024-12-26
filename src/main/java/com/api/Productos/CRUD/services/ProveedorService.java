package com.api.Productos.CRUD.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.Productos.CRUD.models.dtos.ProveedorDTO;
import com.api.Productos.CRUD.models.entities.Proveedor;
import com.api.Productos.CRUD.repositories.IProveedorRepository;

@Service
public class ProveedorService {
    
    @Autowired
    private IProveedorRepository proveedorRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    public List<ProveedorDTO> getProveedores() {

        // Obtener todos los proveedores
        List<Proveedor> proveedores = proveedorRepository.findAll();
        
        // Mapear los proveedores a DTOs
        return proveedores.stream()
            .map(proveedor -> modelMapper.map(proveedor, ProveedorDTO.class))
            .collect(Collectors.toList());
        }

    public Optional<ProveedorDTO> getProveedorById(Long id) {

        // Buscar un proveedor por su id
        Optional<Proveedor> proveedor = proveedorRepository.findById(id);


        // Mapear el proveedor a un DTO
        return Optional.ofNullable(proveedor.map(p -> modelMapper.map(p, ProveedorDTO.class)).orElse(null));
    }

    public ProveedorDTO saveProveedor(Proveedor proveedor) {

        // Guardar el proveedor en la base de datos
        Proveedor crearProveedor = proveedorRepository.save(proveedor);

        // Mapear el proveedor guardado a un DTO
        return modelMapper.map(crearProveedor, ProveedorDTO.class);
    }

    public void deleteProveedor(Long id) {
        proveedorRepository.deleteById(id);
    }

    public ProveedorDTO updateProveedor(Long id, ProveedorDTO proveedorDTO) {
        // Buscar el proveedor existente por id
        Optional<Proveedor> existingProveedorOpt = proveedorRepository.findById(id);
        if (existingProveedorOpt.isEmpty()) {
            throw new RuntimeException("Proveedor no encontrado");
        }
    
        // Mapear el DTO a la entidad Proveedor
        Proveedor proveedor = modelMapper.map(proveedorDTO, Proveedor.class);
        proveedor.setId(id); // Asegurarse de que el id esté establecido
    
        // Guardar el proveedor actualizado en la base de datos
        Proveedor updatedProveedor = proveedorRepository.save(proveedor);
        
        // Mapear la entidad Proveedor actualizada a ProveedorDTO
        ProveedorDTO updatedProveedorDTO = modelMapper.map(updatedProveedor, ProveedorDTO.class);
        
        // Devolver el ProveedorDTO actualizado
        return updatedProveedorDTO;
    }

    
}

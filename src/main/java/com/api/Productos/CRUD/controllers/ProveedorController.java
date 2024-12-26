package com.api.Productos.CRUD.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.Productos.CRUD.models.dtos.ProveedorDTO;
import com.api.Productos.CRUD.models.entities.Proveedor;
import com.api.Productos.CRUD.services.ProveedorService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {
    
    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Obtener todos los proveedores")
    @GetMapping("/")
    public ResponseEntity<List<ProveedorDTO>> getProveedores() {

        //Obtener la lista de proveedores
        List<ProveedorDTO> proveedores = proveedorService.getProveedores();

        //Si la lista está vacía, devolver un código 204
        if(proveedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(proveedores);
        }
    }

    @Operation(summary = "Obtener un proveedor por id")
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> getProveedorById(@PathVariable Long id) {

        //Obtener el proveedor por id
        return proveedorService.getProveedorById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Guardar un proveedor")
    @PostMapping
    public ResponseEntity<Void> saveProveedor(@RequestBody @Valid ProveedorDTO proveedorDTO) {
        try{
            //Guardar el proveedor
            proveedorService.saveProveedor(modelMapper.map(proveedorDTO, Proveedor.class));
            return ResponseEntity.status(HttpStatus.CREATED).build(); //Devolver un código 201
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //Devolver un código 400
        }
    }

    @Operation(summary = "Eliminar un proveedor por id")
    @DeleteMapping("/delete/{id}")
    public void deleteProveedor(Long id) {
        proveedorService.deleteProveedor(id);
    }
    
    @Operation(summary = "Actualizar un proveedor por id")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProveedor(@PathVariable Long id, @RequestBody @Valid ProveedorDTO proveedorDTO) {
        try {
            // Actualizar el proveedor
            ProveedorDTO updatedProveedorDTO = proveedorService.updateProveedor(id, proveedorDTO);
            return ResponseEntity.ok(updatedProveedorDTO);
            
        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("Proveedor no encontrado");

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body("Solicitud incorrecta");
        }
    }

}

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

import com.api.Productos.CRUD.models.dtos.ProductoDTO;
import com.api.Productos.CRUD.models.entities.Producto;
import com.api.Productos.CRUD.models.entities.Proveedor;
import com.api.Productos.CRUD.services.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Obtener todos los productos")
    @GetMapping("/")
    public ResponseEntity<List<ProductoDTO>> getProductos() {

        // Obtener la lista de productos
        List<ProductoDTO> productos = productoService.getProductos();

        if(productos.isEmpty()) {
            return ResponseEntity.noContent().build(); // Si la lista esta vacia, devolver un 204
        } else {
            return ResponseEntity.ok(productos); // 200 ok y lista de productos
        }
    }

    @Operation(summary = "Obtener un producto por id")
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getProductoById(@PathVariable Long id) {

        // Obtener el producto por id
        return productoService.getProductoById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
            
    }

    @Operation(summary = "Guardar un producto")
    @PostMapping
    public ResponseEntity<ProductoDTO> saveProducto(@RequestBody @Valid ProductoDTO productoDTO) {
        try {

            // Mapear el DTO a la entidad
            Producto producto = modelMapper.map(productoDTO, Producto.class);
            
            // Asignar el proveedor al producto
            if (productoDTO.getProveedorId() != null) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(productoDTO.getProveedorId());
                producto.setProveedor(proveedor);
            }
            
            // Guardar el producto
            ProductoDTO savedProductoDTO = productoService.saveProducto(producto);

            // Devolver el producto guardado
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProductoDTO);

        } catch (Exception e) {

            // Si ocurre un error, devolver un 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Eliminar un producto por id")
    @DeleteMapping("/delete/{id}")
    public void deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
    }

    @Operation(summary = "Actualizar un producto por id")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable Long id, @RequestBody @Valid ProductoDTO productoDTO) {
        try {
            // Actualizar el producto
            ProductoDTO updatedProductoDTO = productoService.updateProducto(id, productoDTO);
            return ResponseEntity.ok(updatedProductoDTO);
        } catch (RuntimeException e) {

            // Si ocurre un error, devolver un 404 y el mensaje Producto no encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("Producto no encontrado");
        } catch (Exception e) {

            // Si ocurre un error, devolver un 400 y el mensaje Solicitud incorrecta
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body("Solicitud incorrecta");
        }
    }
}
package com.api.Productos.CRUD.models.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 20, message = "El nombre debe tener entre 2 y 20 caracteres")
    private String nombre;

    @NotNull(message = "La descripción no puede ser nula")
    @Size(min = 10, max = 200, message = "La descripción debe tener entre 10 y 200 caracteres")
    private String descripcion;

    @NotNull(message = "El precio no puede ser nulo")
    @Positive(message = "El precio debe ser un número positivo")
    private Double precio;

    private Long proveedorId; 
    
    private String proveedorNombre;

}

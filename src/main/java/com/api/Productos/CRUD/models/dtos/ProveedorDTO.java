package com.api.Productos.CRUD.models.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorDTO {

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 20, message = "El nombre debe tener entre 2 y 20 caracteres")
    private String nombre;

    @NotNull(message = "La dirección no puede ser nula")
    @Size(min = 5, max = 200, message = "La dirección debe tener entre 5 y 200 caracteres")
    private String direccion;

    @NotNull(message = "El teléfono no puede ser nulo")
    @Size(min = 10, max = 10, message = "El teléfono debe tener 10 caracteres")
    private String telefono;
}
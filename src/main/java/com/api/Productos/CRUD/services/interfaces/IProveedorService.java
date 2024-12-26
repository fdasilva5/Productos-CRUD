package com.api.Productos.CRUD.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.api.Productos.CRUD.models.dtos.ProveedorDTO;
import com.api.Productos.CRUD.models.entities.Proveedor;

public interface IProveedorService {
    public List<ProveedorDTO> getProveedores();
    public Optional<ProveedorDTO> getProveedorById(Long id);
    public ProveedorDTO saveProveedor(Proveedor proveedor);
    public void deleteProveedor(Long id);
    public ProveedorDTO updateProveedor(Proveedor proveedor);
}

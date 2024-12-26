package com.api.Productos.CRUD.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.api.Productos.CRUD.models.dtos.ProveedorDTO;
import com.api.Productos.CRUD.models.entities.Proveedor;
import com.api.Productos.CRUD.repositories.IProveedorRepository;

public class ProveedorServiceTest {

    @Mock
    private IProveedorRepository proveedorRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProveedorService proveedorService;

    private Proveedor proveedor;

    private ProveedorDTO proveedorDTO;
    


    @SuppressWarnings("deprecation")
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        proveedor = new Proveedor();
       

        proveedorDTO = new ProveedorDTO();
        proveedorDTO.setNombre("Proveedor 1");
        proveedorDTO.setDireccion("Direccion 1");
        proveedorDTO.setTelefono("123456789");


    }


    @Test
     void testSaveProveedor() {
        when(modelMapper.map(any(ProveedorDTO.class), any())).thenReturn(proveedor);
        when(proveedorRepository.save(any(Proveedor.class))).thenReturn(proveedor);
        when(modelMapper.map(any(Proveedor.class), any())).thenReturn(proveedorDTO);

        ProveedorDTO savedProveedorDTO = proveedorService.saveProveedor(proveedor);

        assertNotNull(savedProveedorDTO);
        verify(proveedorRepository, times(1)).save(proveedor);
    }
}

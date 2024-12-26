package com.api.Productos.CRUD.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.Optional;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.modelmapper.ModelMapper;

import com.api.Productos.CRUD.models.dtos.ProductoDTO;
import com.api.Productos.CRUD.models.entities.Producto;
import com.api.Productos.CRUD.repositories.IProductoRespository;


public class ProductoServiceTest {

    @Mock
    private IProductoRespository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Mock
    private ModelMapper modelMapper;

    private AutoCloseable closeable;

    private Producto producto;

    private ProductoDTO productoDTO;


    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        producto = new Producto();

        productoDTO = new ProductoDTO();
        productoDTO.setNombre("Producto 1");
        productoDTO.setPrecio(1000.0);
        productoDTO.setProveedorId(null);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close(); // Liberar recursos
    }

    @Test
    void testGetProductos() {
        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto));
        when(modelMapper.map(producto, ProductoDTO.class)).thenReturn(productoDTO);

        List<ProductoDTO> productos = productoService.getProductos();
        assertNotNull(productos);
        assertEquals(1, productos.size());
        assertEquals("Producto 1", productos.get(0).getNombre());
        assertEquals(1000.0, productos.get(0).getPrecio()); 
        assertEquals(null, productos.get(0).getProveedorId());

    }


    //Verifica que se lance una excepción si el producto no se encuentra
    @Test
    void testUpdateProductoNotFound() {
        Long id = 1L;
        when(productoRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productoService.updateProducto(id, productoDTO);
        });
        assertEquals("Producto no encontrado", exception.getMessage());

        verify(productoRepository, times(1)).findById(id);
        verify(productoRepository, times(0)).save(producto);
    }
}

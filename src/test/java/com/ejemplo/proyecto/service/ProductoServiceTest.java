package com.ejemplo.proyecto.service;

import com.ejemplo.proyecto.model.Producto;
import com.ejemplo.proyecto.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void guardarProducto_DeberiaRetornarProductoGuardado() {
        Producto producto = new Producto(null, "Teclado", 49.99);
        Producto productoGuardado = new Producto(1L, "Teclado", 49.99);
        when(productoRepository.save(producto)).thenReturn(productoGuardado);

        Producto resultado = productoService.guardar(producto);

        assertThat(resultado).isSameAs(productoGuardado);
        verify(productoRepository).save(producto);
    }

    @Test
    void obtenerPorId_CuandoExiste_DeberiaRetornarProducto() {
        Producto producto = new Producto(1L, "Teclado", 49.99);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto resultado = productoService.obtenerPorId(1L);

        assertThat(resultado).isSameAs(producto);
        verify(productoRepository).findById(1L);
    }

    @Test
    void obtenerPorId_CuandoNoExiste_DeberiaLanzarExcepcion() {
        when(productoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productoService.obtenerPorId(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Producto no encontrado");
        verify(productoRepository).findById(1L);
    }
}

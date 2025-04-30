package org.example.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.example.Clases.Catalogo;
import org.example.Clases.Libro;
import org.example.Clases.Prestamo;
import org.example.Gestores.SistemaPrestamos;
import org.example.Enum.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

public class SistemaPrestamosTest {
    private Libro elAlquimista;
    private Libro cienAñosSoledad;
    private Libro martinFierro;

    @Mock
    private Catalogo catalogo;


    @InjectMocks
    private SistemaPrestamos sistemaPrestamos;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sistemaPrestamos = new SistemaPrestamos(catalogo);
        elAlquimista = new Libro("111-1-11-111111-0", "El Alquimista", "Paulo Coelho", Estado.DISPONIBLE);
        cienAñosSoledad = new Libro("111-1-11-111111-2", "Cien años de soledad", "Gabriel García Márquez", Estado.DISPONIBLE);
        martinFierro = new Libro("111-1-11-111111-1", "El Gaucho Martín Fierro", "José Hernández", Estado.PRESTADO);

    }
    @Test
    void testPrestarLibroExitoso() {
        when(catalogo.buscarPorISBN("111-1-11-111111-0")).thenReturn(List.of(elAlquimista));
        Prestamo prestamo = sistemaPrestamos.prestarLibro("111-1-11-111111-0");
        assertNotNull(prestamo);
        assertEquals(elAlquimista, prestamo.getLibro());
        assertEquals(LocalDate.now(), prestamo.getFechaPrestamo());
        assertEquals(Estado.PRESTADO, elAlquimista.getEstado());
        verify(catalogo).buscarPorISBN("111-1-11-111111-0");
        assertEquals(1, sistemaPrestamos.getPrestamos().size());
    }
    @Test
    void testPrestarLibroNoDisponible() {
        when(catalogo.buscarPorISBN("111-1-11-111111-1")).thenReturn(List.of(martinFierro));
        Prestamo prestamo = sistemaPrestamos.prestarLibro("111-1-11-111111-1");
        assertNull(prestamo);
        assertEquals(Estado.PRESTADO, martinFierro.getEstado());
        assertEquals(0, sistemaPrestamos.getPrestamos().size());
        verify(catalogo).buscarPorISBN("111-1-11-111111-1");
    }

    @Test
    void testMultiplesPrestamos() {
        when(catalogo.buscarPorISBN("111-1-11-111111-0")).thenReturn(List.of(elAlquimista));
        when(catalogo.buscarPorISBN("111-1-11-111111-2")).thenReturn(List.of(cienAñosSoledad));
        Prestamo prestamo1 = sistemaPrestamos.prestarLibro("111-1-11-111111-0");
        Prestamo prestamo2 = sistemaPrestamos.prestarLibro("111-1-11-111111-2");
        assertNotNull(prestamo1);
        assertNotNull(prestamo2);
        assertEquals(2, sistemaPrestamos.getPrestamos().size());
        assertEquals(Estado.PRESTADO, elAlquimista.getEstado());
        assertEquals(Estado.PRESTADO, cienAñosSoledad.getEstado());
    }
}
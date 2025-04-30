package org.example.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.example.Clases.Catalogo;
import org.example.Clases.*;
import org.example.Clases.Prestamo;
import org.example.Enum.Estado;
import org.example.Gestores.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
public class UsuarioTest {
    @Mock
    private Catalogo catalogo;

    @Mock
    private SistemaPrestamos sistemaPrestamos;

    @InjectMocks
    private GestionUsuarios gestionUsuarios;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        gestionUsuarios = new GestionUsuarios(catalogo, sistemaPrestamos);
        gestionUsuarios.registrarUsuario(new Usuario("Alexis Yassuff"));
    }


    @Test
    void testRegistrarPrestamoExitoso() {
        Libro elAlquimista = new Libro("111-1-11-111111-0", "El Alquimista", "Paulo Coelho", Estado.DISPONIBLE);
        Prestamo prestamo = new Prestamo(elAlquimista);

        when(catalogo.buscarPorISBN(elAlquimista.getISBN())).thenReturn(List.of(elAlquimista));
        when(sistemaPrestamos.prestarLibro(elAlquimista.getISBN())).thenReturn(prestamo);

        gestionUsuarios.registrarPrestamo("Alexis Yassuff", elAlquimista.getISBN());

        Usuario usuario = gestionUsuarios.obtenerUsuario("Alexis Yassuff");
        assertEquals(1, usuario.getHistorialPrestamos().size());
        assertEquals(elAlquimista, usuario.getHistorialPrestamos().get(0).getLibro());

        verify(catalogo).buscarPorISBN(elAlquimista.getISBN());
        verify(sistemaPrestamos).prestarLibro(elAlquimista.getISBN());
    }

    @Test
    void testRegistrarPrestamoUsuarioNoRegistrado() {
        String isbn = "123456";
        assertThrows(IllegalArgumentException.class, () -> {
            gestionUsuarios.registrarPrestamo("usuarioInexistente", isbn);
        });
    }

    @Test
    void testRegistrarPrestamoLibroNoDisponible() {
        Libro martinFierro = new Libro("111-1-11-111111-1", "El Gaucho Martín Fierro", "José Hernández", Estado.PRESTADO);
        when(catalogo.buscarPorISBN(martinFierro.getISBN())).thenReturn(List.of(martinFierro));
        when(sistemaPrestamos.prestarLibro(martinFierro.getISBN())).thenReturn(null);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            gestionUsuarios.registrarPrestamo("Alexis Yassuff", martinFierro.getISBN());
        });

        assertEquals("No se pudo realizar el préstamo", exception.getMessage());

        verify(catalogo).buscarPorISBN(martinFierro.getISBN());
        verify(sistemaPrestamos).prestarLibro(martinFierro.getISBN());
    }

    @Test
    void testRegistrarPrestamoISBNInexistente() {
        when(catalogo.buscarPorISBN("999-9-99-999999-9")).thenReturn(List.of());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gestionUsuarios.registrarPrestamo("Alexis Yassuff", "999-9-99-999999-9");
        });

        assertEquals("Libro no encontrado", exception.getMessage());

        verify(catalogo).buscarPorISBN("999-9-99-999999-9");
        verify(sistemaPrestamos, never()).prestarLibro(anyString());
    }



    @Test
    void testRegistrarDosPrestamosConsecutivos() {
        Libro elAlquimista = new Libro("111-1-11-111111-0", "El Alquimista", "Paulo Coelho", Estado.DISPONIBLE);
        Libro cienAñosSoledad = new Libro("111-1-11-111111-2", "Cien años de soledad", "Gabriel García Márquez", Estado.DISPONIBLE);
        Prestamo prestamo1 = new Prestamo(elAlquimista);
        Prestamo prestamo2 = new Prestamo(cienAñosSoledad);

        when(catalogo.buscarPorISBN(elAlquimista.getISBN())).thenReturn(List.of(elAlquimista));
        when(catalogo.buscarPorISBN(cienAñosSoledad.getISBN())).thenReturn(List.of(cienAñosSoledad));
        when(sistemaPrestamos.prestarLibro(elAlquimista.getISBN())).thenReturn(prestamo1);
        when(sistemaPrestamos.prestarLibro(cienAñosSoledad.getISBN())).thenReturn(prestamo2);

        gestionUsuarios.registrarPrestamo("Alexis Yassuff", elAlquimista.getISBN());
        gestionUsuarios.registrarPrestamo("Alexis Yassuff", cienAñosSoledad.getISBN());

        Usuario usuario = gestionUsuarios.obtenerUsuario("Alexis Yassuff");
        assertEquals(2, usuario.getHistorialPrestamos().size());
        assertEquals(elAlquimista, usuario.getHistorialPrestamos().get(0).getLibro());
        assertEquals(cienAñosSoledad, usuario.getHistorialPrestamos().get(1).getLibro());
    }





}

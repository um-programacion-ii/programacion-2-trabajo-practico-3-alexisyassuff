package org.example;
import org.example.Clases.Catalogo;
import org.example.Enum.Estado;
import org.example.Clases.Libro;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {



    @Test
    public void testCrearLibroValido() {
        Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
        assertEquals("978-3-16-148410-0", libro.getISBN());
        assertEquals("Clean Code", libro.getTitulo());
        assertEquals("Robert C. Martin", libro.getAutor());
        assertEquals(Estado.DISPONIBLE, libro.getEstado());
        libro.mostrarLibro();
    }

    @Test
    public void testCambiarEstadoLibro() {
        Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
        libro.setEstado(Estado.PRESTADO);
        assertEquals(Estado.PRESTADO, libro.getEstado());
    }

    @Test
    public void testExistenciaDeLibroEnColeccion() {
        Libro libro1 = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
        Catalogo catalogo = new Catalogo();
        catalogo.agregarLibro(libro1);
        assertTrue(catalogo.obtenerListaDeLibros().contains(libro1));
    }
    @Test
    public void testInxistenciaDeLibroEnColeccion() {
        Libro ElAlqumista = new Libro("971-1-16-144410-0", "El Alqumista", "Paulo Coelho");
        Catalogo catalogo = new Catalogo();
        assertFalse(catalogo.obtenerListaDeLibros().contains(ElAlqumista));
    }


}
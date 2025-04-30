package org.example.Test;

import org.example.Clases.Catalogo;
import org.example.Clases.Libro;
import org.example.Enum.Estado;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibroTest {

    @Test
    public void testCrearLibroValido() {
        Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin", Estado.DISPONIBLE);
        assertEquals("978-3-16-148410-0", libro.getISBN());
        assertEquals("Clean Code", libro.getTitulo());
        assertEquals("Robert C. Martin", libro.getAutor());
        assertEquals(Estado.DISPONIBLE, libro.getEstado());
        libro.mostrarLibro();
    }

    @Test
    public void testCambiarEstadoLibro() {
        Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin", Estado.DISPONIBLE);
        libro.setEstado(Estado.PRESTADO);
        assertEquals(Estado.PRESTADO, libro.getEstado());
    }

    @Test
    public void testExistenciaDeLibroEnColeccion() {
        Libro libro1 = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin", Estado.DISPONIBLE);
        Catalogo catalogo = new Catalogo();
        catalogo.agregarLibro(libro1);
        assertTrue(catalogo.obtenerListaDeLibros().contains(libro1));
    }

    @Test
    public void testInxistenciaDeLibroEnColeccion() {
        Libro elAlquimista = new Libro("111-1-11-111111-0", "El Alquimista", "Paulo Coelho", Estado.DISPONIBLE);
        Catalogo catalogo = new Catalogo();
        assertFalse(catalogo.obtenerListaDeLibros().contains(elAlquimista));
    }

    @Test
    public void testBuscarPorIsbn() {
        Libro elAlquimista = new Libro("111-1-11-111111-0", "El Alquimista", "Paulo Coelho", Estado.DISPONIBLE);
        Catalogo catalogo = new Catalogo();
        catalogo.agregarLibro(elAlquimista);
        List<Libro> librosEncontrados = catalogo.buscarPorISBN("111-1-11-111111-0");
        assertNotNull(librosEncontrados);
        assertEquals(1, librosEncontrados.size());  // Aseguramos que solo haya un libro
        assertEquals("El Alquimista", librosEncontrados.get(0).getTitulo());
    }


    @Test
    public void testBuscarPorIsbnMultiples() {
        Libro elAlquimista = new Libro("111-1-11-111111-0", "El Alquimista", "Paulo Coelho", Estado.DISPONIBLE);
        Libro MartinFierro = new Libro("111-1-11-111111-1", "El Gaucho Martín Fierro", "José Hernández", Estado.PRESTADO);
        Libro CienAñosSoledad = new Libro("111-1-11-111111-2", "Cien años de soledad", "Gabriel García Márquez", Estado.DISPONIBLE);
        Catalogo catalogo = new Catalogo();
        catalogo.agregarLibro(elAlquimista);
        catalogo.agregarLibro(MartinFierro);
        catalogo.agregarLibro(CienAñosSoledad);

        List<Libro> librosEncontrados0 = catalogo.buscarPorISBN("111-1-11-111111-0");
        List<Libro> librosEncontrados1 = catalogo.buscarPorISBN("111-1-11-111111-1");
        List<Libro> librosEncontrados2 = catalogo.buscarPorISBN("111-1-11-111111-2");

        assertNotNull(librosEncontrados0);
        assertNotNull(librosEncontrados1);
        assertNotNull(librosEncontrados2);

        assertEquals(1, librosEncontrados0.size());  // Aseguramos que solo haya un libro para cada ISBN
        assertEquals(1, librosEncontrados1.size());
        assertEquals(1, librosEncontrados2.size());

        assertEquals("El Alquimista", librosEncontrados0.get(0).getTitulo());
        assertEquals("El Gaucho Martín Fierro", librosEncontrados1.get(0).getTitulo());
        assertEquals("Cien años de soledad", librosEncontrados2.get(0).getTitulo());
    }

    @Test
    public void testBuscarPorIsbnErroneo() {
        Libro elAlquimista = new Libro("111-1-11-111111-0", "El Alquimista", "Paulo Coelho", Estado.DISPONIBLE);
        Libro MartinFierro = new Libro("111-1-11-111111-1", "El Gaucho Martín Fierro", "José Hernández", Estado.DISPONIBLE);
        Libro CienAñosSoledad = new Libro("111-1-11-111111-2", "Cien años de soledad", "Gabriel García Márquez", Estado.PRESTADO);
        Catalogo catalogo = new Catalogo();
        catalogo.agregarLibro(elAlquimista);
        catalogo.agregarLibro(MartinFierro);
        catalogo.agregarLibro(CienAñosSoledad);

        List<Libro> librosEncontrados = catalogo.buscarPorISBN("111-1-11-111111-2");

        assertNotNull(librosEncontrados);
        assertEquals(1, librosEncontrados.size());  // Aseguramos que solo haya un libro con ese ISBN
        assertNotEquals("El Alquimista", librosEncontrados.get(0).getTitulo());
    }
}

package org.example;
import org.example.Clases.Catalogo;
import org.example.Enum.Estado;
import org.example.Clases.Libro;
import org.junit.Test;
import java.util.stream.Collectors;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class Main {



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
        Libro libroEncontrado = catalogo.buscarPorISBN("111-1-11-111111-0");
        assertNotNull(libroEncontrado);
        assertEquals("El Alquimista", libroEncontrado.getTitulo());
    }

    @Test
    public void testBuscarPorIsbnMultiples() {
        Libro elAlquimista = new Libro("111-1-11-111111-0", "El Alquimista", "Paulo Coelho", Estado.DISPONIBLE);
        Libro MartinFierro = new Libro("111-1-11-111111-1" ,"El Gaucho Martín Fierro", "José Hernández", Estado.PRESTADO);
        Libro CienAñosSoledad = new Libro("111-1-11-111111-2","Cien años de soledad", "Gabriel García Márquez", Estado.DISPONIBLE);
        Catalogo catalogo = new Catalogo();
        catalogo.agregarLibro(elAlquimista);
        catalogo.agregarLibro(MartinFierro);
        catalogo.agregarLibro(CienAñosSoledad);
        Libro libroEncontrado = catalogo.buscarPorISBN("111-1-11-111111-0");
        Libro libroEncontrado1 = catalogo.buscarPorISBN("111-1-11-111111-1");
        Libro libroEncontrado2 = catalogo.buscarPorISBN("111-1-11-111111-2");
        assertNotNull(libroEncontrado);
        assertNotNull(libroEncontrado1);
        assertNotNull(libroEncontrado2);
        assertEquals("El Alquimista", libroEncontrado.getTitulo());
        assertEquals("El Gaucho Martín Fierro", libroEncontrado1.getTitulo());
        assertEquals("Cien años de soledad", libroEncontrado2.getTitulo());

    }
    @Test
    public void testBuscarPorIsbnErroneo() {
        Libro elAlquimista = new Libro("111-1-11-111111-0", "El Alquimista", "Paulo Coelho", Estado.DISPONIBLE);
        Libro MartinFierro = new Libro("111-1-11-111111-1" ,"El Gaucho Martín Fierro", "José Hernández", Estado.DISPONIBLE);
        Libro CienAñosSoledad = new Libro("111-1-11-111111-2","Cien años de soledad", "Gabriel García Márquez", Estado.PRESTADO);
        Catalogo catalogo = new Catalogo();
        catalogo.agregarLibro(elAlquimista);
        catalogo.agregarLibro(MartinFierro);
        catalogo.agregarLibro(CienAñosSoledad);
        Libro libroEncontrado = catalogo.buscarPorISBN("111-1-11-111111-2");
        assertNotNull(libroEncontrado);
        // Que no sea igual
        assertNotEquals("El Alquimista", libroEncontrado.getTitulo());
    }

    @Test
    public void testObtenerLibrosDisponibles() {
        Libro elAlquimista = new Libro("111-1-11-111111-0", "El Alquimista", "Paulo Coelho", Estado.DISPONIBLE);
        Libro martinFierro = new Libro("111-1-11-111111-1", "El Gaucho Martín Fierro", "José Hernández", Estado.DISPONIBLE);
        Libro cienAniosSoledad = new Libro("111-1-11-111111-2", "Cien años de soledad", "Gabriel García Márquez", Estado.PRESTADO);
        Catalogo catalogo = new Catalogo();
        catalogo.agregarLibro(elAlquimista);
        catalogo.agregarLibro(martinFierro);
        catalogo.agregarLibro(cienAniosSoledad);
        List<Libro> disponibles = catalogo.obtenerLibrosDisponibles();
        assertEquals(2, disponibles.size());
    }


}
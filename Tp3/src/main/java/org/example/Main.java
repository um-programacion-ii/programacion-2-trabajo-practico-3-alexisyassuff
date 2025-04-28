package org.example;
import org.example.Enum.Estado;
import org.example.Clases.Libro;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
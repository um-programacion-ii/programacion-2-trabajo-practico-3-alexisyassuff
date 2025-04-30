package org.example.Gestores;
import org.example.Clases.Catalogo;
import org.example.Clases.Libro;
import org.example.Clases.Prestamo;
import org.example.Enum.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaPrestamos {
    List<Prestamo> prestamos = new ArrayList<>();
    private Catalogo catalogo;


    public SistemaPrestamos(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public Prestamo prestarLibro(String isbn) {
        List<Libro> libros = catalogo.buscarPorISBN(isbn);
        if (!libros.isEmpty()) {
            Libro libro = libros.get(0);
            if (libro.getEstado() == Estado.DISPONIBLE) {
                libro.setEstado(Estado.PRESTADO);
                Prestamo prestamo = new Prestamo(libro);
                prestamos.add(prestamo);
                return prestamo;
            }
        }
        return null;
    }



    public List<Prestamo> getPrestamos() {
        return prestamos;
    }
}

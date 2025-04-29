package org.example.Clases;
import org.example.Enum.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaPrestamos {
    List<Prestamo> prestamos = new ArrayList<>();

    public boolean prestarLibro(Libro libro) {
        if (libro.getEstado() == Estado.DISPONIBLE) {
            libro.setEstado(Estado.PRESTADO);
            prestamos.add(new Prestamo(libro));
            return true;
        }
        return false;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }
}

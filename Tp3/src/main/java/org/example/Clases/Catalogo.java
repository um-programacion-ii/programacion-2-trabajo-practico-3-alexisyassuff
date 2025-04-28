package org.example.Clases;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    List<Libro> lista = new ArrayList<>();

    public void agregarLibro(Libro libro){
        lista.add(libro);
    }

    public List<Libro> obtenerListaDeLibros() {
        return new ArrayList<>(lista);  // Devolvés una copia
    }

}

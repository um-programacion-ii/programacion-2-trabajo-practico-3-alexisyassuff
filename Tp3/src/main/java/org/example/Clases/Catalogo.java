package org.example.Clases;
import org.example.Enum.Estado;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Catalogo {
    private List<Libro> lista = new ArrayList<>();

    public void agregarLibro(Libro libro){
        lista.add(libro);
    }

    public List<Libro> obtenerListaDeLibros() {
        return new ArrayList<>(lista);  // Devolvés una copia
    }

    public Libro buscarPorISBN(String ISBN) {
        return lista.stream()
                .filter(r -> r.getISBN().equalsIgnoreCase(ISBN))
                .findFirst()
                .orElse(null);
    }

    public List<Libro> obtenerLibrosDisponibles() {
        return lista.stream()
                .filter(r -> r.getEstado() == Estado.DISPONIBLE)
                .collect(Collectors.toList());
    }}



package org.example.Gestores;
import java.util.HashMap;
import java.util.Map;
import org.example.Clases.*;
import java.util.List;


public class GestionUsuarios {
    private Map<String, Usuario> usuarios = new HashMap<>();

    private Catalogo catalogo;
    private SistemaPrestamos sistemaPrestamos;

    public GestionUsuarios(Catalogo catalogo, SistemaPrestamos sistemaPrestamos) {
        this.catalogo = catalogo;
        this.sistemaPrestamos = sistemaPrestamos;
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getNombre(), usuario);
    }

    public void registrarPrestamo(String nombreUsuario, String isbn) {
        Usuario usuario = usuarios.get(nombreUsuario);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no registrado");
        }

        List<Libro> libros = catalogo.buscarPorISBN(isbn);
        if (libros.isEmpty()) {
            throw new IllegalArgumentException("Libro no encontrado");
        }
        Libro libro = libros.get(0);

        Prestamo prestamo = sistemaPrestamos.prestarLibro(isbn);
        if (prestamo == null) {
            throw new IllegalStateException("No se pudo realizar el préstamo");
        }

        usuario.agregarPrestamo(prestamo);
    }

}

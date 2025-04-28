package org.example.Clases;
import org.example.Enum.*;

public class Libro {
    private String ISBN;
    private String titulo;
    private String autor;
    private Estado estado;

    public Libro(String ISBN, String titulo, String autor) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.estado = estado.DISPONIBLE;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void mostrarLibro(){
        System.out.println("Titulo: "+  getTitulo());
        System.out.println("Autor: "+ getAutor());
        System.out.println("ISBN: "+  getISBN());
        System.out.println("Estado: "+  getEstado());
    }
}
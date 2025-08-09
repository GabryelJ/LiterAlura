package dev.gabryel.literalura.model.livro;

import dev.gabryel.literalura.model.Idioma;
import dev.gabryel.literalura.model.autor.Autor;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "livros_autores",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autores;

    @Enumerated(EnumType.STRING)
    private List<Idioma> idiomas;

    private Long numeroDownloads;

    public Livro() {
    }

    public Livro(String titulo) {
        this.titulo = titulo;
    }

    public Livro(String titulo, List<Autor> autores, List<Idioma> idiomas, Long numeroDownloads) {
        this.titulo = titulo;
        this.autores = autores;
        this.idiomas = idiomas;
        this.numeroDownloads = numeroDownloads;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<Idioma> idiomas) {
        this.idiomas = idiomas;
    }

    public Long getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Long numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    @Override
    public String toString() {
        return "Livro de titulo : " + titulo +
                " dos autores: " + autores +
                " disponível nos idiomas: " + idiomas +
                " com o numero de downloads: " + numeroDownloads + '.';
    }
}

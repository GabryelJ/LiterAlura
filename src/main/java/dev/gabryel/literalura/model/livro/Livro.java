package dev.gabryel.literalura.model.livro;

import dev.gabryel.literalura.model.Idioma;
import dev.gabryel.literalura.model.autor.Autor;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "livros_autores",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autores;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "livro_idiomas", joinColumns = @JoinColumn(name = "livro_id"))
    @Column(name = "idioma")
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

        String autoresFormatados = autores.stream()
                .map(Autor::getNome)
                .collect(Collectors.joining(", "));

        String idiomasFormatados = idiomas.stream()
                .map(Idioma::getIdioma)
                .collect(Collectors.joining(", "));

        return "Livro de titulo : " + titulo +
                ", dos autores: " + autoresFormatados +
                ", disponível no(s) idioma(s): " + idiomasFormatados +
                ", com o numero de downloads: " + numeroDownloads + '.';
    }
}

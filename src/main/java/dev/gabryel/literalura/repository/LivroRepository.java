package dev.gabryel.literalura.repository;

import dev.gabryel.literalura.model.Idioma;
import dev.gabryel.literalura.model.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTituloContainingIgnoreCase(String titulo);

    List<Livro> findByIdioma(Idioma idioma);
}

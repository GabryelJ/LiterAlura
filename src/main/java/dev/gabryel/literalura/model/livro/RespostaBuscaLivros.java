package dev.gabryel.literalura.model.livro;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RespostaBuscaLivros(
        @JsonAlias("results") List<LivroData> livros
) {
}

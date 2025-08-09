package dev.gabryel.literalura.model.livro;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.gabryel.literalura.model.autor.AutorData;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroData(
        @JsonAlias("title")String titulo,
        @JsonAlias("authors")List<AutorData> autores,
        @JsonAlias("languages")List<String> idiomas,
        @JsonAlias("download_count")Long numeroDownloads) {
}

package dev.gabryel.literalura.service;

import dev.gabryel.literalura.model.Idioma;
import dev.gabryel.literalura.model.autor.Autor;
import dev.gabryel.literalura.model.autor.AutorData;
import dev.gabryel.literalura.model.livro.RespostaBuscaLivros;
import dev.gabryel.literalura.model.livro.Livro;
import dev.gabryel.literalura.model.livro.LivroData;
import dev.gabryel.literalura.repository.AutorRepository;
import dev.gabryel.literalura.repository.LivroRepository;
import dev.gabryel.literalura.service.convertedados.ConversorDeDados;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final String URL_BASE = "https://gutendex.com/";

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final ConsumoAPI consumoAPI;
    private final ConversorDeDados conversorDeDados;

    public LivroService(LivroRepository livroRepository, AutorRepository autorRepository, ConsumoAPI consumoAPI, ConversorDeDados conversorDeDados) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.consumoAPI = consumoAPI;
        this.conversorDeDados = conversorDeDados;
    }

    private void cadastraLivro(Livro livro){
        livroRepository.save(livro);
    }

    private LivroData getLivroData(String json) {
        return conversorDeDados.obterDados(json, LivroData.class);
    }

    private RespostaBuscaLivros obterListaLivrosData(String json){
        return conversorDeDados.obterDados(json, RespostaBuscaLivros.class);
    }

    private List<Autor> obterListaAutores(List<AutorData> autoresData){
        return autoresData.stream().map(autor -> new Autor(autor.nome(), autor.anoNascimento(), autor.anoFalecimento()))
                .collect(Collectors.toList());
    }

    private Autor buscaAutorCadastrado(Autor autor){
        Optional<Autor> autorOptional = autorRepository.findByNome(autor.getNome());
        if (autorOptional.isPresent()){
            return autorOptional.get();
        }
        return autor;
    }

    private Optional<Livro> buscaLivroPorNome(String titulo){
        return livroRepository.findByTitulo(titulo);
    }

    @Transactional
    public void cadastraLivro(String nomeLivro){
        Optional<Livro> livroCadastrado = buscaLivroPorNome(nomeLivro);

        if (livroCadastrado.isPresent()){
            System.out.println("Livro já está cadastrado.");
            System.out.println(livroCadastrado.get());
            return;
        }

        String Url = URL_BASE + "/books?search=" + nomeLivro.replace(" ", "+");
        String json = consumoAPI.buscarDados(Url);
        RespostaBuscaLivros listaLivrosData = obterListaLivrosData(json);
        Optional<LivroData> livroData = listaLivrosData.livros().stream().findFirst();

        if (livroData.isPresent()){
            LivroData presentLivroData = livroData.get();

            List<Autor> autoresVerificados = presentLivroData.autores().stream()
                    .map(autorData -> new Autor(autorData.nome(), autorData.anoNascimento(), autorData.anoFalecimento()))
                    .map(this::buscaAutorCadastrado)
                    .collect(Collectors.toList());

            List<Idioma> idiomas = presentLivroData.idiomas().stream()
                    .map(Idioma::fromAbreviacao)
                    .collect(Collectors.toList());

            Livro novoLivro = new Livro(presentLivroData.titulo(), autoresVerificados, idiomas, presentLivroData.numeroDownloads());

            System.out.println("Livro encontrado e cadastrado com sucesso!");
            System.out.println(novoLivro);

            livroRepository.save(novoLivro);
            return;
        }

        System.out.println("Livro não encontrado na API.");
    }
}

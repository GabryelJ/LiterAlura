package dev.gabryel.literalura.main;

import dev.gabryel.literalura.model.Idioma;
import dev.gabryel.literalura.model.autor.Autor;
import dev.gabryel.literalura.model.livro.Livro;
import dev.gabryel.literalura.service.AutorService;
import dev.gabryel.literalura.service.LivroService;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Component
public class Main {

    private final Scanner entrada = new Scanner(System.in);
    private final LivroService livroService;
    private final AutorService autorService;

    public Main(LivroService livroService, AutorService autorService) {
        this.livroService = livroService;
        this.autorService = autorService;
    }

    public void menuInicial() {
        var option = -1;
        String menu = """
                \n
                Opções disponíveis:
                                
                1 - Buscar livro pelo titulo;
                2 - Listar livros registrados;
                3 - Listar autores registrados;
                4 - Listar autores vivos em um dado ano;
                5 - Listar livros em um determinado idioma;
                                
                0 - Sair.
               
                Escolha o numero da opção desejada: """;

        while (option != 0) {
            System.out.printf(menu);
            option = entrada.nextInt();
            entrada.nextLine();

            switch (option) {
                case 1:
                    menuBuscarLivroPorTitulo();
                    break;
                case 2:
                    menuListarLivrosRegistrados();
                    break;
                case 3:
                    menuListarAutoresRegistrados();
                    break;
                case 4:
                    menuListarAutoresVivosNoAno();
                    break;
                case 5:
                    menuListarLivrosDeUmIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void menuBuscarLivroPorTitulo(){
        System.out.println("Insira o titulo do livro que deseja buscar: ");
        String titulo = entrada.nextLine();
        if (titulo.isEmpty()) {
            System.out.println("O título não pode ser vazio.");
            return;
        }
        livroService.cadastraLivro(titulo);
    }

    private void menuListarLivrosRegistrados(){
        System.out.println("Livros registrados no banco de dados: ");
        livroService.buscarLivrosCadastrados().forEach(System.out::println);
    }

    private void menuListarAutoresRegistrados(){
        System.out.println("Autores registrados no banco de dados: ");

        autorService.buscarAutoresCadastrados().forEach(System.out::println);
    }

    private void menuListarAutoresVivosNoAno(){
        System.out.println("Para qual ano deseja verificar quais autores estavam vivos?");
        try {
            int anoAutoresVivos = entrada.nextInt();
            entrada.nextLine();

            List<Autor> autoresVivos = autorService.buscarAutoresVivosNoAno(anoAutoresVivos);

            if (autoresVivos.isEmpty()) {
                System.out.println("Não foram encontrados autores vivos neste ano.");
            } else {
                autoresVivos.forEach(System.out::println);
            }

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, insira um ano válido (somente números).");
            entrada.nextLine();
        }
    }

    private void menuListarLivrosDeUmIdioma(){
        String menu = """
            Idiomas disponíveis:
            
            es - Espanhol;
            en - Inglês;
            fr - Francês;
            pt - Português.
            
            """;

        System.out.printf(menu);
        System.out.printf("Insira a sigla do idioma em que deseja realizar a busca:");
        String idioma = entrada.nextLine().trim();

        Idioma eIdioma = Idioma.fromAbreviacao(idioma);

        if (eIdioma.equals(Idioma.OUTRO)) {
            System.out.println("Sigla de idioma inválida. Por favor, escolha uma das opções.");
            return;
        }

        List<Livro> livrosEncontrados = livroService.buscarLivrosDeDeterminadoIdioma(eIdioma);

        if (livrosEncontrados.isEmpty()) {
            System.out.println("Não foram encontrados livros para este idioma.");
        } else {
            livrosEncontrados.forEach(System.out::println);
        }
    }
}

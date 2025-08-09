package dev.gabryel.literalura.main;

import dev.gabryel.literalura.service.AutorService;
import dev.gabryel.literalura.service.LivroService;
import org.springframework.stereotype.Component;

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
                Escolha o número da sua opção:
                                
                1 - Buscar livro pelo titulo;
                2 - Listar livros registrados;
                3 - Listar autores registrados;
                4 - Listar autores vivos em um dado ano;
                5 - Listar livros em um determinado idioma;
                                
                0 - Sair.
                \n
                """;

        while (option != 0) {
            System.out.println(menu);
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
        Integer anoAutoresVivos = entrada.nextInt();
        entrada.nextLine();
        autorService.buscarAutoresVivosNoAno(anoAutoresVivos).forEach(System.out::println);
    }

    private void menuListarLivrosDeUmIdioma(){
        String menu = """
                    Insira o idioma em que deseja realizar a busca:
                    
                    es - Espanhol;
                    en - Inglês; 
                    fr - Francês;
                    pt - Português.
                """;
        System.out.println(menu);

        String idioma = entrada.nextLine();
        System.out.println("Não implementado");
    }
}

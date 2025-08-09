package dev.gabryel.LiterAlura.main;

import org.springframework.stereotype.Component;

@Component
public class Main {

    public void exibirMenu() {
        var menu = """
                Escolha o número da sua opção: 
                
                1 - Buscar livro pelo titulo;
                2 - Listar livros registrados;
                3 - Listar autores registrados;
                4 - Listar autores vivos em um dado ano;
                5 - Listar livros em um determinado idioma;
                
                0 - Sair.
                """;

        System.out.println(menu);
    }
}

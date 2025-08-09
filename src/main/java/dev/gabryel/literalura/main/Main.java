package dev.gabryel.literalura.main;

import dev.gabryel.literalura.service.ConsumoAPI;
import org.springframework.stereotype.Component;

@Component
public class Main {

    private ConsumoAPI consumoAPI;

    public Main(ConsumoAPI consumoAPI) {
        this.consumoAPI = consumoAPI;
    }

    public void exibirMenu() {
        var menu = """
                Escolha o número da sua opção: 
                
                teste - /books
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

package dev.gabryel.literalura.service;


import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Service
public class ConsumoAPI {

    private final HttpClient cliente = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .build();

    public String buscarDados(String address) {
        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(address))
                .build();
        HttpResponse<String> resposta = null;
        try {
            resposta = cliente
                    .send(requisicao, HttpResponse.BodyHandlers.ofString());
            return resposta.body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

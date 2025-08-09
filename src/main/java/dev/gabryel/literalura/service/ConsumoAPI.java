package dev.gabryel.literalura.service;


import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Service
public class ConsumoAPI {

    public String buscarDados(String address) {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(address))
                .build();
        HttpResponse<String> resposta = null;
        try {
            resposta = cliente
                    .send(requisicao, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String json = resposta.body();
        return json;
    }

}

package dev.gabryel.literalura.service.convertedados;

public interface IConversorDeDados {
    <T> T obterDados (String json, Class<T> tClass);
}

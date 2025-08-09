package dev.gabryel.literalura.service.convertedados;

public interface IConversorDeDados {
    <T> T getDados (String json, Class<T> tClass);
}

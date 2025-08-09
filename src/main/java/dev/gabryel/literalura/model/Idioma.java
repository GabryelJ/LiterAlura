package dev.gabryel.literalura.model;

public enum Idioma {
    INGLES("en"),
    PORTUGUES("pt"),
    FRANCES("fr"),
    ESPANHOL("es"),
    OUTRO("outro");

    private String abreviacaoIdioma;
    Idioma(String abreviacaoIdioma) {
        this.abreviacaoIdioma = abreviacaoIdioma;
    }

    public static Idioma fromAbreviacao(String abreviacaoIdioma) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.abreviacaoIdioma.equalsIgnoreCase(abreviacaoIdioma)) {
                return idioma;
            }
        }
        return OUTRO;
    }

    public String getAbreviacaoIdiomao() {
        return abreviacaoIdioma;
    }

}

package dev.gabryel.literalura.model;

public enum Idioma {
    INGLES("inglês", "en"),
    PORTUGUES("português", "pt"),
    FRANCES("francês", "fr"),
    ESPANHOL("espanhol", "es"),
    OUTRO("outro");

    private String idioma;
    private String abreviacaoIdioma;

    Idioma(String idioma, String abreviacaoIdioma) {
        this.idioma = idioma;
        this.abreviacaoIdioma = abreviacaoIdioma;
    }

    Idioma(String idioma ) {
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

    public String getIdioma() {
        return idioma;
    }
}

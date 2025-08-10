package dev.gabryel.literalura.service;

import dev.gabryel.literalura.model.autor.Autor;
import dev.gabryel.literalura.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    private AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> buscarAutoresCadastrados(){
        return autorRepository.findAll();
    }

    public List<Autor> buscarAutoresVivosNoAno(Integer ano){
        return autorRepository.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqualOrAnoFalecimentoIsNull(ano, ano);
    }
}

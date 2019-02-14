package api.validator.helper;

import api.model.ComplexoEolico;
import api.repository.ComplexoEolicoRepository;
import api.repository.ParqueEolicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ComplexoEolicoValidatorHelper{
    @Autowired
    private ComplexoEolicoRepository complexoEolicoRepository;

    @Autowired
    private ParqueEolicoRepository parqueEolicoRepository;


    public Boolean existeComplexoEolicoInformado(ComplexoEolico entidade) {
        Optional<ComplexoEolico> complexoEolicoCadastrado = complexoEolicoRepository.findByNome(entidade.getNome());
        return complexoEolicoCadastrado.isPresent() && entidade.getId() != complexoEolicoCadastrado.get().getId();
    }

    public Boolean existeParqueVinculado(ComplexoEolico entidade) {
        return !parqueEolicoRepository.findByComplexoEolico(entidade).isEmpty();
    }

}







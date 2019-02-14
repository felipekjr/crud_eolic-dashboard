package api.validator.helper;

import api.model.ParqueEolico;
import api.repository.AerogeradorRepository;
import api.repository.ParqueEolicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * Created by Gustavo Galvao on 25/09/2018.
 */

@Component
public class ParqueEolicoValidatorHelper{

    @Autowired
    private ParqueEolicoRepository parqueEolicoRepository;
    @Autowired
    private AerogeradorRepository aerogeradorRepository;

    public Boolean existeParqueEolicoComNomeInformado(ParqueEolico entidade) {
        Optional<ParqueEolico> parqueEolicoCadastrado = parqueEolicoRepository.findByNome(entidade.getNome());
        return parqueEolicoCadastrado.isPresent() && entidade.getId() != parqueEolicoCadastrado.get().getId();
    }

    public Boolean existeAerogeradorVinculado(ParqueEolico entidade) {
        return !aerogeradorRepository.findByParqueEolico(entidade).isEmpty();
    }

}


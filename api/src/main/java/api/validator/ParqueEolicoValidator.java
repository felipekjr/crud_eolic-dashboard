package api.validator;

import api.arq.validator.AbstractCRUDValidador;
import api.arq.validator.dto.ApiErroCodigo;
import api.model.ParqueEolico;
import api.validator.helper.ParqueEolicoValidatorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Gustavo Galvao on 25/09/2018.
 */

@Component
public class ParqueEolicoValidator extends AbstractCRUDValidador<ParqueEolico>{
    @Autowired
    private ParqueEolicoValidatorHelper parqueEolicoValidatorHelper;

    public void validarAntesDeSalvar(ParqueEolico entidade) {
        verificarErro(parqueEolicoValidatorHelper.existeParqueEolicoComNomeInformado(entidade),
                ApiErroCodigo.JA_EXISTE.toString());
    }

    public void validarAntesDeRemover(ParqueEolico entidade) {
        verificarErro(parqueEolicoValidatorHelper.existeAerogeradorVinculado(entidade),
                ApiErroCodigo.EXISTE_AEROGERADOR_VINCULADO.toString());
    }

}





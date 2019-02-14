package api.validator;

import api.arq.validator.AbstractCRUDValidador;
import api.arq.validator.dto.ApiErroCodigo;
import api.model.Aerogerador;
import api.validator.helper.AerogeradorValidatorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AerogeradorValidator extends AbstractCRUDValidador<Aerogerador>{
    @Autowired
    private AerogeradorValidatorHelper aerogeradorValidatorHelper;

   @Override
   public void validarAntesDeSalvar(Aerogerador entidade) {
        verificarErro(aerogeradorValidatorHelper.existeAerogeradorComNomeInformado(entidade),
                ApiErroCodigo.JA_EXISTE.toString());
        verificarErro(aerogeradorValidatorHelper.parqueEolicoNaoExiste(entidade),
                ApiErroCodigo.PARQUE_EOLICO_NAO_EXISTE.toString());
    }
    @Override
    public void validarAntesDeRemover(Aerogerador entidade) {
    }
}






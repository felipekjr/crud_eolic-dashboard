package api.validator;

import api.arq.modelo.AbstractEntity;
import api.arq.validator.AbstractCRUDValidador;
import api.arq.validator.dto.ApiErroCodigo;
import api.model.ComplexoEolico;
import api.validator.helper.ComplexoEolicoValidatorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

@Component
public class ComplexoEolicoValidator extends AbstractCRUDValidador<ComplexoEolico>  {
    @Autowired
    private ComplexoEolicoValidatorHelper complexoEolicoValidatorHelper;

    @Override
    public void validarAntesDeSalvar(ComplexoEolico entidade) {
        verificarErro(complexoEolicoValidatorHelper.existeComplexoEolicoInformado(entidade),
                ApiErroCodigo.JA_EXISTE.toString());
    }

    @Override
    public void validarAntesDeRemover(ComplexoEolico entidade) {
        verificarErro(complexoEolicoValidatorHelper.existeParqueVinculado(entidade),
                ApiErroCodigo.EXISTE_PARQUE_VINCULADO.toString());
    }
}







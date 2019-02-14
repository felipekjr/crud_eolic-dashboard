package api.validator;

import api.arq.validator.AbstractCRUDValidador;
import api.arq.validator.dto.ApiErroCodigo;
import api.model.Usuario;
import api.validator.helper.UsuarioValidatorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidator extends AbstractCRUDValidador<Usuario>{
    @Autowired
    private UsuarioValidatorHelper usuarioValidatorHelper;
    @Override
    public void validarAntesDeSalvar(Usuario entidade) {
//        verificarErro(!usuarioValidatorHelper.senhasInformadasConferem(entidade),
//                ApiErroCodigo.SENHAS_NAO_CONFEREM.toString());

    }

    public void validarAntesDeRemover(Usuario entidade) {
//
    }
}




package api.validator;

import api.arq.validator.dto.ApiErroCodigo;
import api.controller.dto.ParametrosLogin;
import api.validator.helper.UsuarioValidatorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public abstract class LoginValidator implements Validator {
    @Autowired
    UsuarioValidatorHelper usuarioValidatorHelper;

    @Override
    public void validate(Object target, Errors errors) {
        ParametrosLogin parametrosLogin = ((ParametrosLogin) target);
        if (!usuarioValidatorHelper.usuarioAutenticado(parametrosLogin)) {
            errors.reject(ApiErroCodigo.SENHA_OU_USUARIO_INCORRETO.toString());
        }
    }

    @Override
    public boolean supports(Class<?> clazz){
        return ParametrosLogin.class.isAssignableFrom(clazz);
    }

}

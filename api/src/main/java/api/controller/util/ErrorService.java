package api.controller.util;

import org.springframework.beans.factory.annotation.Autowired;

public class ErrorService {
    @Autowired
    public ApiInternalError setErrors(String campo, Object valorRejeitado, String mensagem){
        ApiInternalError erro = new ApiInternalError();
        erro.setCampo(campo);
        erro.setValorRejeitado(valorRejeitado);
        erro.setMensagem(mensagem);
        return erro;
    }

}

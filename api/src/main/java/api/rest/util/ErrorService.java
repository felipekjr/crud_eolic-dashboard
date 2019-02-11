package api.rest.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.ws.rs.BeanParam;

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

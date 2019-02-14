package api.arq.exception;

import api.arq.validator.Erros;
import org.springframework.stereotype.Component;

@Component
public class RequisicaoInvalidaException extends RuntimeException{
    public Erros erros;
    public RequisicaoInvalidaException(Erros erros) {
        super();
    }
    public void verificar(Erros erros) throws Exception {
        if (erros.existeErro())
            throw new RequisicaoInvalidaException(erros);
    }

    public Erros getErros() {
        return erros;
    }

    public void setErros(Erros erros) {
        this.erros = erros;
    }
}

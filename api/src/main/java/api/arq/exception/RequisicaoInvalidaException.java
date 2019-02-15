package api.arq.exception;

import api.arq.validator.Erros;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

public class RequisicaoInvalidaException extends RuntimeException{
    public Erros erros = new Erros();

    public void verificar(Erros erros) {
        if (erros !=null && erros.existeErro())
            throw new RequisicaoInvalidaException(erros);
    }

    public RequisicaoInvalidaException() {
        super();
    }

    public RequisicaoInvalidaException(Erros erros) {
        this.erros = erros;
    }

    public RequisicaoInvalidaException(String s, Erros erros) {
        super(s);
        this.erros = erros;
    }
}

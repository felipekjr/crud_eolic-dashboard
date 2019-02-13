package api.arq.validator;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractValidator {
    @Autowired
    public Erros erros;

    public void verificarErro(Boolean existeErro,  String codigoErro) {
        if (existeErro){
            erros.adicionarErro(codigoErro);
        }
    }
}

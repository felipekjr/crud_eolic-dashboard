package api.arq.validator;


import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Component
@RequestScope
public abstract class Erros{
    private List<String> erros;

    public void adicionarErro(String erro) {
        erros.add(erro);
    }
    public Boolean existeErro() {
        return !erros.isEmpty();
    }
    public List<String> getErros() {
        return  erros;
    }

}

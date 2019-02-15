package api.arq.validator;


import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.ArrayList;
import java.util.List;

@Component
@RequestScope
public class Erros{
    public ArrayList<String> erros = new ArrayList<String>();

    public void adicionarErro(String erro) {
        erros.add(erro);
    }
    public Boolean existeErro() {
        return !erros.isEmpty();
    }

    public Erros() {
    }

    public Erros(ArrayList<String> erros) {
        this.erros = erros;
    }

    public ArrayList<String> getErros() {
        return erros;
    }

    public void setErros(ArrayList<String> erros) {
        this.erros = erros;
    }
}

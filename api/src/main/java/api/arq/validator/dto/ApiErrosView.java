package api.arq.validator.dto;

import java.util.List;

public class ApiErrosView{
    List<ApiFieldErro> fieldErros;
    List<ApiErroGeral> errosGerais;
    public ApiErrosView(List<ApiFieldErro> fieldErros, List<ApiErroGeral> erroGerais){
        super();
        this.fieldErros = fieldErros;
        this.errosGerais = erroGerais;
    }
}

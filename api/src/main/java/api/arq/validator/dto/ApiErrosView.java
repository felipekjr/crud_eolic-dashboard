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

    public List<ApiFieldErro> getFieldErros() {
        return fieldErros;
    }

    public void setFieldErros(List<ApiFieldErro> fieldErros) {
        this.fieldErros = fieldErros;
    }

    public List<ApiErroGeral> getErrosGerais() {
        return errosGerais;
    }

    public void setErrosGerais(List<ApiErroGeral> errosGerais) {
        this.errosGerais = errosGerais;
    }
}

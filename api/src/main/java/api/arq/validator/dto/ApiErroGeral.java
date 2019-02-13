package api.arq.validator.dto;

public class ApiErroGeral{
    String codigo;
    public ApiErroGeral(String codigo){
        super();
        this.codigo = codigo;
    }
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}

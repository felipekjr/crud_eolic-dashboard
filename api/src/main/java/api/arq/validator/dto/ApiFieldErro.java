package api.arq.validator.dto;
public class ApiFieldErro {
    String field;
    String codigo;
    Object valorRejeitado;

    public ApiFieldErro(String field, String codigo, Object valorRejeitado) {
        this.field = field;
        this.codigo = codigo;
        this.valorRejeitado = valorRejeitado;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Object getValorRejeitado() {
        return valorRejeitado;
    }

    public void setValorRejeitado(Object valorRejeitado) {
        this.valorRejeitado = valorRejeitado;
    }
}

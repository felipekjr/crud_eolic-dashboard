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
}

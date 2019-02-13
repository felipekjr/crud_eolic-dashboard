package api.arq.exception;

import api.arq.validator.dto.ApiErroCodigo;

public class ApiErroGeralException extends Exception{
    private final ApiErroCodigo codigo;

    public ApiErroGeralException(ApiErroCodigo codigo) {
        super(codigo.name());
        this.codigo = codigo;
    }

    public ApiErroGeralException(String message, Throwable cause, ApiErroCodigo codigo) {
        super(message, cause);
        this.codigo = codigo;
    }

    public ApiErroGeralException(String message, ApiErroCodigo codigo) {
        super(message);
        this.codigo = codigo;
    }

    public ApiErroGeralException(Throwable cause, ApiErroCodigo codigo) {
        super(cause);
        this.codigo = codigo;
    }

    public ApiErroCodigo getCode() {
        return this.codigo;
    }
}


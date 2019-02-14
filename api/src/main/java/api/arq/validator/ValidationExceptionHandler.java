package api.arq.validator;

import api.arq.exception.ApiErroGeralException;
import api.arq.exception.RequisicaoInvalidaException;
import api.arq.validator.dto.ApiErroGeral;
import api.arq.validator.dto.ApiErrosView;
import api.arq.validator.dto.ApiFieldErro;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.awt.peer.CanvasPeer;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by Gustavo Galvao on 26/07/2018.
 */

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler{

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid( MethodArgumentNotValidException exception,
     HttpHeaders headers,  HttpStatus status, WebRequest request)  {
        BindingResult bindingResult = exception.getBindingResult();
        ApiErrosView apiErrorsView = processarErrosSistema(bindingResult.getFieldErrors(), bindingResult.getGlobalErrors());
        return  new ResponseEntity(apiErrorsView, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ApiErroGeralException.class)
    public ResponseEntity<Object> handleApiErroGeralException(ApiErroGeralException exception, WebRequest request){
        List<ApiErroGeral> apiGlobalErrors = Arrays.asList(new ApiErroGeral(exception.getCode().toString()));
        ApiErrosView apiErrorsView = new ApiErrosView(Collections.emptyList(), apiGlobalErrors);
        return new ResponseEntity(apiErrorsView, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequisicaoInvalidaException.class)
    public ResponseEntity<Object>handleRequisicaoInvalida(RequisicaoInvalidaException exception, WebRequest request) {
        List<ApiErroGeral> apiGlobalErrors = exception.erros.getErros().stream().map(ApiErroGeral::new).collect(Collectors.toList());
        ApiErrosView apiErrorsView = new ApiErrosView(Collections.emptyList(), apiGlobalErrors);
        return new ResponseEntity(apiErrorsView, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private ApiErrosView processarErrosSistema(List<FieldError> fieldErrors, List<ObjectError> globalErrors) {
        List<ApiFieldErro> apiFieldErrors = fieldErrors.stream().map(fieldError -> new ApiFieldErro(
                fieldError.getField(), fieldError.getCode(), fieldError.getRejectedValue()))
                .collect(Collectors.toList());

        List<ApiErroGeral> apiGlobalErrors = globalErrors.stream().map(globalError -> new ApiErroGeral(globalError.getCode()))
                .collect(Collectors.toList());
        return new ApiErrosView(apiFieldErrors, apiGlobalErrors);
    }
}

package api.controller;

import api.arq.rest.CRUDController;
import api.model.ComplexoEolico;
import api.repository.ComplexoEolicoRepository;
import api.controller.util.ApiInternalError;
import api.controller.util.ErrorService;
import api.service.ComplexoEolicoService;
import api.validator.ComplexoEolicoValidator;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/complexo_eolico")
public class ComplexoEolicoController extends CRUDController<ComplexoEolico> {
    @Autowired
    private ComplexoEolicoService complexoEolicoService;
    @Autowired
    private ComplexoEolicoValidator complexoEolicoValidator;
}



package api.controller;

import api.arq.rest.CRUDController;
import api.model.ParqueEolico;
import api.service.ParqueEolicoService;
import api.validator.ParqueEolicoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Gustavo Galvao on 25/09/2018.
 */

@RestController
@RequestMapping("/api/parque_eolico")
public class ParqueEolicoController extends  CRUDController<ParqueEolico>{
    @Autowired
    private ParqueEolicoService parqueEolicoService;
    @Autowired
    private ParqueEolicoValidator parqueEolicoValidator;
}


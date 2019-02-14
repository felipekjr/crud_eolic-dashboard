package api.controller;

import api.arq.rest.CRUDController;
import api.model.Aerogerador;
import api.service.AerogeradorService;
import api.validator.AerogeradorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aerogerador")
public class AerogeradorController extends CRUDController<Aerogerador>{
    @Autowired
    private AerogeradorService aerogeradorService;
    @Autowired
    private AerogeradorValidator aerogeradorValidator;
}




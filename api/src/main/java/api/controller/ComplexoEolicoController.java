package api.controller;

import api.exception.ResourceNotFoundException;
import api.model.ComplexoEolico;
import api.repository.ComplexoEolicoRepository;
import api.controller.util.ApiInternalError;
import api.controller.util.ErrorService;
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
@RequestMapping("/api")
public class ComplexoEolicoController {
	@Autowired
    ComplexoEolicoRepository complexoEolicoRepository;
	ErrorService errorService;

	@GetMapping("/complexo_eolico")
	public List<ComplexoEolico> getAllComplexoEolico() {
	    return complexoEolicoRepository.findAll();
	}

	@GetMapping("/complexo_eolico/{id}")
	public ComplexoEolico getComplexoEolicoById(@PathVariable(value = "id") Long complexoEolicoId) {
	    return complexoEolicoRepository.findById(complexoEolicoId)
	            .orElseThrow(() -> new ResourceNotFoundException("ComplexoEolico", "id", complexoEolicoId));
	}

    @PostMapping("/complexo_eolico")
    public ResponseEntity<?> createComplexoEolico(@Valid @RequestBody ComplexoEolico complexoEolico) {
            Optional<ComplexoEolico> optionalComplexoEolico = complexoEolicoRepository.findById(complexoEolico.getId());
            if(!optionalComplexoEolico.isPresent()){
                complexoEolicoRepository.save(complexoEolico);
                return ResponseEntity.ok("ComplexoEolico cadastrado com sucesso!");
            }else{
                ArrayList<ApiInternalError> erros = new ArrayList<>();
                ApiInternalError erro = errorService.setErrors("complexoEolicoId", complexoEolico, "Ja existe um complexoEolico com este id");
                erros.add(erro);
                return new ResponseEntity<>(erros, new HttpHeaders(), HttpStatus.UNAUTHORIZED);//
            }
    }

	@PutMapping("/complexo_eolico/{id}")
	public ResponseEntity<?> updateComplexoEolico(@PathVariable(value = "id") Long complexoEolicoId,
	                                        @Valid @RequestBody ComplexoEolico complexoEolicoDetails) {
	    Optional<ComplexoEolico> optionalComplexoEolico = complexoEolicoRepository.findById(complexoEolicoId);
        if(optionalComplexoEolico.isPresent()){
            optionalComplexoEolico.get().setNome(complexoEolicoDetails.getNome());
            optionalComplexoEolico.get().setUf(complexoEolicoDetails.getUf());
            optionalComplexoEolico.get().setIdentificador(complexoEolicoDetails.getIdentificador());
            complexoEolicoRepository.save(optionalComplexoEolico.get());
            return ResponseEntity.ok("Complexo Eolico atualizado com sucesso!");
        }else{
            ArrayList<ApiInternalError> erros = new ArrayList<>();
            ApiInternalError erro = errorService.setErrors("complexoEolicoId", complexoEolicoId, "Nao existe um complexoEolico com este id");
            erros.add(erro);
            return new ResponseEntity<>(erros, new HttpHeaders(), HttpStatus.UNAUTHORIZED);//
        }
	}

	@DeleteMapping("/complexo_eolico/{id}")
	public ResponseEntity<?> deleteComplexoEolico(@PathVariable(value = "id") Long complexoEolicoId) {
	    Optional<ComplexoEolico> optionalComplexoEolico = complexoEolicoRepository.findById(complexoEolicoId);
	    if(optionalComplexoEolico.isPresent()){
            complexoEolicoRepository.delete(optionalComplexoEolico.get());
            return ResponseEntity.ok("Complexo deletado com sucesso");
        }else{
            ArrayList<ApiInternalError> erros = new ArrayList<>();
            ApiInternalError erro = errorService.setErrors("complexoEolicoId", complexoEolicoId, "Nao existe um complexoEolico com este id");
            erros.add(erro);
            return new ResponseEntity<>(erros, new HttpHeaders(), HttpStatus.UNAUTHORIZED);//
        }
	}

}

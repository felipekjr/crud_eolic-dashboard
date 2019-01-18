package api.service;

import api.exception.ResourceNotFoundException;
import api.model.ComplexoEolico;
import api.dao.ComplexoEolicoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ComplexoEolicoService {
	@Autowired
	ComplexoEolicoDao complexoEolicoDao;

    // Coletar todos os usuários
	@GetMapping("/complexo_eolico")
	public List<ComplexoEolico> getAllComplexoEolico() {
	    return complexoEolicoDao.findAll();
	}
    // Criar novo usuário
	@PostMapping("/complexo_eolico")
	public ComplexoEolico createComplexoEolico(@Valid @RequestBody ComplexoEolico complexoEolico) {
	    return complexoEolicoDao.save(complexoEolico);
	}
    // Coleta um único ComplexoEolico
	@GetMapping("/complexo_eolico/{id}")
	public ComplexoEolico getComplexoEolicoById(@PathVariable(value = "id") Long complexoEolicoId) {
	    return complexoEolicoDao.findById(complexoEolicoId)
	            .orElseThrow(() -> new ResourceNotFoundException("ComplexoEolico", "id", complexoEolicoId));
	}
    // Atualizar ComplexoEolico
	@PutMapping("/complexo_eolico/{id}")
	public ComplexoEolico updateComplexoEolico(@PathVariable(value = "id") Long ComplexoEolicoId,
	                                        @Valid @RequestBody ComplexoEolico complexoEolicoDetails) {

	    ComplexoEolico complexoEolico = complexoEolicoDao.findById(ComplexoEolicoId)
	            .orElseThrow(() -> new ResourceNotFoundException("ComplexoEolico", "id", ComplexoEolicoId));

	    complexoEolico.setNome(complexoEolicoDetails.getNome());
	    complexoEolico.setUf(complexoEolicoDetails.getUf());
	    complexoEolico.setIdentificador(complexoEolicoDetails.getIdentificador());
	    	    

	    ComplexoEolico updatedComplexoEolico = complexoEolicoDao.save(complexoEolico);
	    return updatedComplexoEolico;
	}
    // DeletarComplexoEolico
	@DeleteMapping("/complexo_eolico/{id}")
	public ResponseEntity<?> deleteComplexoEolico(@PathVariable(value = "id") Long complexoEolicoId) {
	    ComplexoEolico complexoEolico = complexoEolicoDao.findById(complexoEolicoId)
	            .orElseThrow(() -> new ResourceNotFoundException("ComplexoEolico", "id", complexoEolicoId));

	    complexoEolicoDao.delete(complexoEolico);
	    return ResponseEntity.ok().build();
	}

}

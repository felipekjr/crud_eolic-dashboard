package api.service;

import api.exception.ResourceNotFoundException;
import api.model.ParqueEolico;
import api.dao.ParqueEolicoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ParqueEolicoService {
	@Autowired
	ParqueEolicoDao parqueEolicoDao;

    // Coletar todos os usuários	
	@GetMapping("/parque_eolico")
	public List<ParqueEolico> getAllParqueEolico() {
	    return parqueEolicoDao.findAll();
	}
    // Criar novo usuário
	@PostMapping("/parque_eolico")
	public ParqueEolico createParqueEolico(@Valid @RequestBody ParqueEolico parqueEolico) {		
	    return parqueEolicoDao.save(parqueEolico);
	}
    // Coleta um único ParqueEolico
	@GetMapping("/parque_eolico/{id}")
	public ParqueEolico getParqueEolicoById(@PathVariable(value = "id") Long parqueEolicoId) {
	    return parqueEolicoDao.findById(parqueEolicoId)
	            .orElseThrow(() -> new ResourceNotFoundException("ParqueEolico", "id", parqueEolicoId));
	}
    // Atualizar ParqueEolico
	@PutMapping("/parque_eolico/{id}")
	public ParqueEolico updateParqueEolico(@PathVariable(value = "id") Long ParqueEolicoId,
	                                        @Valid @RequestBody ParqueEolico parqueEolicoDetails) {

	    ParqueEolico parqueEolico = parqueEolicoDao.findById(ParqueEolicoId)
	            .orElseThrow(() -> new ResourceNotFoundException("ParqueEolico", "id", ParqueEolicoId));

	    parqueEolico.setNome(parqueEolicoDetails.getNome());
	    parqueEolico.setLatitude(parqueEolicoDetails.getLatitude());
	    parqueEolico.setLongitude(parqueEolicoDetails.getLongitude());
	    parqueEolico.setPotenciaInstalada(parqueEolicoDetails.getPotenciaInstalada());	    
	    parqueEolico.setComplexoEolico(parqueEolicoDetails.getComplexoEolico());	    

	    ParqueEolico updatedParqueEolico = parqueEolicoDao.save(parqueEolico);
	    return updatedParqueEolico;
	}
    // DeletarParqueEolico
	@DeleteMapping("/parque_eolico/{id}")
	public ResponseEntity<?> deleteParqueEolico(@PathVariable(value = "id") Long parqueEolicoId) {
	    ParqueEolico parqueEolico = parqueEolicoDao.findById(parqueEolicoId)
	            .orElseThrow(() -> new ResourceNotFoundException("ParqueEolico", "id", parqueEolicoId));

	    parqueEolicoDao.delete(parqueEolico);
	    return ResponseEntity.ok().build();
	}

}

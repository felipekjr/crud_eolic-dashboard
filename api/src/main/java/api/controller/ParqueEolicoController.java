package api.controller;

import api.exception.ResourceNotFoundException;
import api.model.ParqueEolico;
import api.repository.ParqueEolicoRepository;
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

@RestController
@RequestMapping("/api")
public class ParqueEolicoController {
	@Autowired
	ParqueEolicoRepository parqueEolicoRepository;
	ErrorService errorService;

	@GetMapping("/parque_eolico")
	public List<ParqueEolico> getAllParqueEolico() {
	    return parqueEolicoRepository.findAll();
	}


	@GetMapping("/parque_eolico/{id}")
	public ParqueEolico getParqueEolicoById(@PathVariable(value = "id") Long parqueEolicoId) {
	    return parqueEolicoRepository.findById(parqueEolicoId)
	            .orElseThrow(() -> new ResourceNotFoundException("ParqueEolico", "id", parqueEolicoId));
	}

	@PostMapping("/parque_eolico")
	public ResponseEntity<?> createParqueEolico(@Valid @RequestBody ParqueEolico parqueEolico) {
		Optional<ParqueEolico> optionalParqueEolico = parqueEolicoRepository.findById(parqueEolico.getId());
		if(!optionalParqueEolico.isPresent()){
			parqueEolicoRepository.save(parqueEolico);
			return ResponseEntity.ok("Parque Eolico cadastrado com sucesso!");
		}else{
			ArrayList<ApiInternalError> erros = new ArrayList<>();
			ApiInternalError erro = errorService.setErrors("parqueEolicoId", parqueEolico, "Ja existe um Parque Eolico com este id");
			erros.add(erro);
			return new ResponseEntity<>(erros, new HttpHeaders(), HttpStatus.UNAUTHORIZED);//
		}

	}
	
    // Atualizar ParqueEolico
	@PutMapping("/parque_eolico/{id}")
	public ResponseEntity<?> updateParqueEolico(@PathVariable(value = "id") Long ParqueEolicoId,
	                                        @Valid @RequestBody ParqueEolico parqueEolicoDetails) {

	    Optional<ParqueEolico> optionalParqueEolico = parqueEolicoRepository.findById(ParqueEolicoId);
	    if(optionalParqueEolico.isPresent()){
			optionalParqueEolico.get().setNome(parqueEolicoDetails.getNome());
			optionalParqueEolico.get().setLatitude(parqueEolicoDetails.getLatitude());
			optionalParqueEolico.get().setLongitude(parqueEolicoDetails.getLongitude());
			optionalParqueEolico.get().setPotenciaInstalada(parqueEolicoDetails.getPotenciaInstalada());
			optionalParqueEolico.get().setComplexoEolico(parqueEolicoDetails.getComplexoEolico());
			parqueEolicoRepository.save(optionalParqueEolico.get());
			return ResponseEntity.ok("Parque Eolico atualizado com sucesso!");
		}else{
			ArrayList<ApiInternalError> erros = new ArrayList<>();
			ApiInternalError erro = errorService.setErrors("parqueEolicoId", parqueEolicoDetails, "Nao existe um Parque Eolico com este id");
			erros.add(erro);
			return new ResponseEntity<>(erros, new HttpHeaders(), HttpStatus.UNAUTHORIZED);//
		}
	}
	
    // DeletarParqueEolico
	@DeleteMapping("/parque_eolico/{id}")
	public ResponseEntity<?> deleteParqueEolico(@PathVariable(value = "id") Long parqueEolicoId) {
	    Optional<ParqueEolico> optionalParqueEolico = parqueEolicoRepository.findById(parqueEolicoId);
	    if(optionalParqueEolico.isPresent()){
			parqueEolicoRepository.delete(optionalParqueEolico.get());
			return ResponseEntity.ok().build();
		}else{
			ArrayList<ApiInternalError> erros = new ArrayList<>();
			ApiInternalError erro = errorService.setErrors("parqueEolicoId", parqueEolicoId, "Nao existe um Parque Eolico com este id");
			erros.add(erro);
			return new ResponseEntity<>(erros, new HttpHeaders(), HttpStatus.UNAUTHORIZED);//
		}


	}

}

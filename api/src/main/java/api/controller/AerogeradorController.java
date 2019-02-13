package api.controller;

import api.exception.ResourceNotFoundException;
import api.model.Aerogerador;
import api.repository.AerogeradorRepository;
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
public class AerogeradorController {

	@Autowired
    AerogeradorRepository aerogeradorRepository;
    ErrorService errorService;

	@GetMapping("/aerogerador")
	public List<Aerogerador> getAllAerogerador() {
	    return aerogeradorRepository.findAll();
	}

	@GetMapping("/aerogerador/{id}")
	public Aerogerador getAerogeradorById(@PathVariable(value = "id") Long aerogeradorId) {
           return aerogeradorRepository.findById(aerogeradorId)
                    .orElseThrow(() -> new ResourceNotFoundException("Aerogerador", "id", aerogeradorId));
	}

	@PostMapping("/aerogerador")
	public ResponseEntity<?> createAerogerador(@Valid @RequestBody Aerogerador aerogerador) {
	        Optional<Aerogerador> optionalAerogerador = aerogeradorRepository.findById(aerogerador.getId());
	        if(!optionalAerogerador.isPresent()){
                aerogeradorRepository.save(aerogerador);
                return ResponseEntity.ok("Aerogerador cadastrado com sucesso!");
            }else{
                ArrayList<ApiInternalError> erros = new ArrayList<>();
                ApiInternalError erro = errorService.setErrors("aerogeradorId", aerogerador, "Ja existe um aerogerador com este id");
                erros.add(erro);
                return new ResponseEntity<>(erros, new HttpHeaders(), HttpStatus.UNAUTHORIZED);//
            }
	}

	@PutMapping("/aerogerador/{id}")
	public ResponseEntity<?> updateAerogerador(@PathVariable(value = "id") Long aerogeradorId,
	                                        @Valid @RequestBody Aerogerador aerogeradorDetails) {
            Optional<Aerogerador> optionalAerogerador = aerogeradorRepository.findById(aerogeradorId);
            if(optionalAerogerador.isPresent()){
                optionalAerogerador.get().setNome(aerogeradorDetails.getNome());
                optionalAerogerador.get().setLatitude(aerogeradorDetails.getLatitude());
                optionalAerogerador.get().setAlturaTorre(aerogeradorDetails.getAlturaTorre());
                optionalAerogerador.get().setDiametroVarredura(aerogeradorDetails.getDiametroVarredura());
                optionalAerogerador.get().setModelo(aerogeradorDetails.getModelo());
                optionalAerogerador.get().setParqueEolico(aerogeradorDetails.getParqueEolico());
                aerogeradorRepository.save(optionalAerogerador.get());
                return ResponseEntity.ok("Aerogerador atualizado com sucesso!");
            }else{
                ArrayList<ApiInternalError> erros = new ArrayList<>();
                ApiInternalError erro = errorService.setErrors("aerogeradorId", aerogeradorId, "Nao existe um aerogerador com este id");
                erros.add(erro);
                return new ResponseEntity<>(erros, new HttpHeaders(), HttpStatus.UNAUTHORIZED);//
            }
	}

	@DeleteMapping("/aerogerador/{id}")
	public ResponseEntity<?> deleteAerogerador(@PathVariable(value = "id") Long aerogeradorId) {
            Optional<Aerogerador> aerogerador = aerogeradorRepository.findById(aerogeradorId);
            if(aerogerador.isPresent()){
                aerogeradorRepository.delete(aerogerador.get());
                return ResponseEntity.ok("Aerogerador deletado com sucesso!");
            }else{
                ArrayList<ApiInternalError> erros = new ArrayList<>();
                ApiInternalError erro = errorService.setErrors("aerogeradorId", aerogeradorId, "Nao existe um aerogerador com este id.");
                erros.add(erro);
                return new ResponseEntity<>(erros, new HttpHeaders(), HttpStatus.UNAUTHORIZED);//
            }
	}
}

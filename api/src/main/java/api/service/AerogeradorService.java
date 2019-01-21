package api.service;

import api.exception.ResourceNotFoundException;
import api.model.Aerogerador;
import api.dao.AerogeradorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AerogeradorService {
	@Autowired
	AerogeradorDao aerogeradorDao;

    // Coletar todos os usuários	
	@GetMapping("/aerogerador")
	public List<Aerogerador> getAllAerogerador() {
	    return aerogeradorDao.findAll();
	}
    // Criar novo usuário
	@PostMapping("/aerogerador")
	public Aerogerador createAerogerador(@Valid @RequestBody Aerogerador aerogerador) {
	    return aerogeradorDao.save(aerogerador);
	}
    // Coleta um único Aerogerador
	@GetMapping("/aerogerador/{id}")
	public Aerogerador getAerogeradorById(@PathVariable(value = "id") Long aerogeradorId) {
	    return aerogeradorDao.findById(aerogeradorId)
	            .orElseThrow(() -> new ResourceNotFoundException("Aerogerador", "id", aerogeradorId));
	}
    // Atualizar Aerogerador
	@PutMapping("/aerogerador/{id}")
	public Aerogerador updateAerogerador(@PathVariable(value = "id") Long AerogeradorId,
	                                        @Valid @RequestBody Aerogerador aerogeradorDetails) {

	    Aerogerador aerogerador = aerogeradorDao.findById(AerogeradorId)
	            .orElseThrow(() -> new ResourceNotFoundException("Aerogerador", "id", AerogeradorId));

	    aerogerador.setNome(aerogeradorDetails.getNome());
	    aerogerador.setLatitude(aerogeradorDetails.getLatitude());
	    aerogerador.setAlturaTorre(aerogeradorDetails.getAlturaTorre());
	    aerogerador.setDiametroVarredura(aerogeradorDetails.getDiametroVarredura());
	    aerogerador.setModelo(aerogeradorDetails.getModelo());
	    aerogerador.setParqueEolico(aerogeradorDetails.getParqueEolico());

	    Aerogerador updatedAerogerador = aerogeradorDao.save(aerogerador);
	    return updatedAerogerador;
	}
    // DeletarAerogerador	
	@DeleteMapping("/aerogerador/{id}")
	public ResponseEntity<?> deleteAerogerador(@PathVariable(value = "id") Long aerogeradorId) {
	    Aerogerador aerogerador = aerogeradorDao.findById(aerogeradorId)
	            .orElseThrow(() -> new ResourceNotFoundException("Aerogerador", "id", aerogeradorId));

	    aerogeradorDao.delete(aerogerador);
	    return ResponseEntity.ok().build();
	}

}

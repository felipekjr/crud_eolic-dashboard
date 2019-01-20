package api.service;

import api.exception.*;
import api.model.Usuario;
import api.dao.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UsuarioService {
	@Autowired
    UsuarioDao usuarioDao;

    // Coletar todos os usuários	
	@GetMapping("/usuarios")
	public List<Usuario> getAllUsuarios() {
	    return usuarioDao.findAll();
	}
    // Criar novo usuário
	@PostMapping("/usuarios")
	public Usuario createUsuario(@Valid @RequestBody Usuario usuario) {
	    return usuarioDao.save(usuario);
	}
    // Coleta um único Usuario
	@GetMapping("/usuarios/{id}")
	public Usuario getUsuarioById(@PathVariable(value = "id") Long usuarioId) {
	    return usuarioDao.findById(usuarioId)
	            .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));
	}
    // Atualizar Usuario
	@PutMapping("/usuarios/{id}")
	public Usuario updateUsuario(@PathVariable(value = "id") Long UsuarioId,
	                                        @Valid @RequestBody Usuario usuarioDetails) {

	    Usuario usuario = usuarioDao.findById(UsuarioId)
	            .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", UsuarioId));

	    usuario.setUsername(usuarioDetails.getUsername());
	    usuario.setPassword(usuarioDetails.getPassword());

	    Usuario updatedUsuario = usuarioDao.save(usuario);
	    return updatedUsuario;
	}
    // DeletarUsuario
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") Long usuarioId) {
	    Usuario usuario = usuarioDao.findById(usuarioId)
	            .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));

	    usuarioDao.delete(usuario);
	    return ResponseEntity.ok().build();
	}

}

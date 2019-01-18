package api.service;

import api.exception.ResourceNotFoundException;
import api.model.Usuario;
import api.dao.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

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
    // Get a Single Usuario
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

	    usuario.setLogin(usuarioDetails.getLogin());
	    usuario.setSenha(usuarioDetails.getSenha());

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

package api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import api.dao.UsuarioDao;
import api.exception.ResourceNotFoundException;
import api.model.Usuario;

import javax.validation.Valid;
import java.util.List;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@Repository
public class UsuarioService{
	@Autowired
    UsuarioDao usuarioDao;
	@Autowired
	ImplementUserDetailsService userDetailsService;
	
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

	    usuario.setLogin(usuarioDetails.getUsername());
	    usuario.setSenha(usuarioDetails.getPassword());

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
	
	//Loga usuário
	@PostMapping("/login")
	public UserDetails logarUsuario(@Valid @RequestBody Usuario usuario) {
	    return userDetailsService.loadUserByUsername(usuario.getLogin());
	}
	
	

}

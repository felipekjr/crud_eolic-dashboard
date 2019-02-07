package api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import api.repository.usuario.UsuarioRepository;
import api.model.Usuario;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsuarioService{
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	ImplementUserDetailsService userDetailsService;

	@Autowired
	JwtService jwtService;


	// Coletar todos os usuários
	@GetMapping("/usuarios")
	public List<Usuario> getAllUsuarios() {
	    return usuarioRepository.findAll();
	}

	// Coleta um único Usuario
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable(value = "id") Long id) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
		Boolean hasUsuario = optionalUsuario.isPresent();
		if(!hasUsuario){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(optionalUsuario.get());
	}

	//Loga o usuário
	@PostMapping("/login")
	public ResponseEntity<Usuario>  logarUsuario(@Valid @RequestBody ParametrosLogin parametrosLogin) {
		Optional<Usuario>optionalUsuario = usuarioRepository.findByLogin(parametrosLogin.login);
		if (optionalUsuario.isPresent && passwordEncoder.matches(parametrosLogin.senha, optionalUsuario.get().senha)) {
			Usuario usuario = optionalUsuario.get();
			usuario.token = jwtService.toToken(usuario);
			ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}

class ParametrosLogin {
	String login;
	String senha;
}

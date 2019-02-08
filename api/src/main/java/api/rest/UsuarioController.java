package api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import api.repository.usuario.UsuarioRepository;
import api.model.Usuario;
import api.seguranca.jwt.JwtService;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Coletar todos os usuários
	@GetMapping("/usuarios")
	public Iterable<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	// Coleta um único Usuario
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable(value = "id") Long id) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
		Boolean hasUsuario = optionalUsuario.isPresent();
		if (!hasUsuario) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(optionalUsuario.get());
	}

	//Loga o usuário
	@PostMapping("/login")
	public ResponseEntity<Usuario> logarUsuario(@Valid @RequestBody ParametrosLogin parametrosLogin) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findByLogin(parametrosLogin.getLogin());
		if (optionalUsuario.isPresent() && passwordEncoder.matches(parametrosLogin.getSenha(), optionalUsuario.get().getSenha())) {
			Usuario usuario = optionalUsuario.get();
			usuario.setToken(jwtService.toToken(usuario));
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	class ParametrosLogin {
		private String login;
		private String senha;

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}
	}
}

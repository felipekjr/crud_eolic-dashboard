package api.rest;

import api.rest.util.ApiInternalError;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.omg.CORBA.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import api.repository.usuario.UsuarioRepository;
import api.model.Usuario;
import api.seguranca.jwt.JwtService;

import javax.persistence.EntityListeners;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	@Autowired
	public UsuarioRepository usuarioRepository;

	@Autowired
	public JwtService jwtService;

	@Autowired
	public PasswordEncoder passwordEncoder;

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
	public ResponseEntity<String> logarUsuario(@Valid @RequestBody Usuario parametrosLogin) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findByLogin(parametrosLogin.getLogin());
		if (optionalUsuario.isPresent() && passwordEncoder.matches(parametrosLogin.getSenha(), optionalUsuario.get().getSenha())) {
			Usuario usuario = optionalUsuario.get();
			usuario.setToken(jwtService.toToken(usuario));
			return ResponseEntity.ok(usuario.toString());
		} else {
			List erros = new ArrayList <String>();
			erros.add("Usuario/senha encorretos");
			return new ResponseEntity<String>(erros.toString(), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
		}
	}

	public class ParametrosLogin {
		private String login;
		private String senha;
		public ParametrosLogin(){}
		@JsonCreator
		public ParametrosLogin(@JsonProperty("login") String login, @JsonProperty("senha") String senha){
			this.login = login;
			this.senha = senha;
		}
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

package api.rest;

import api.rest.util.ApiInternalError;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import api.repository.usuario.UsuarioRepository;
import api.model.Usuario;
import api.seguranca.jwt.JwtService;

import javax.validation.Valid;
import java.util.ArrayList;
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

	@GetMapping("/usuarios")
	public Iterable<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable(value = "id") Long id) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
		Boolean hasUsuario = optionalUsuario.isPresent();
		if (!hasUsuario) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(optionalUsuario.get());
	}

	@PostMapping("/login")
	public ResponseEntity<Object> logarUsuario(@Valid @RequestBody Usuario parametrosLogin) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findByLogin(parametrosLogin.getLogin());
		if (optionalUsuario.isPresent() && passwordEncoder.matches(parametrosLogin.getSenha(), optionalUsuario.get().getSenha())) {
			Usuario usuario = optionalUsuario.get();
			usuario.setToken(jwtService.toToken(usuario));
			return ResponseEntity.ok(usuario);
		} else {//
			ArrayList<ApiInternalError> erros = new ArrayList<>();
			ApiInternalError erro = new ApiInternalError();
			erro.setCampo("SENHA/USUARIO");
			erro.setValorRejeitado(parametrosLogin);
			erro.setMensagem("Usuario ou senha invalidos");
			erros.add(erro);
			return new ResponseEntity<>(erros, new HttpHeaders(), HttpStatus.UNAUTHORIZED);//
		}
	}

	public class ParametrosLogin {
		private String login;
		private String senha;
	}
}

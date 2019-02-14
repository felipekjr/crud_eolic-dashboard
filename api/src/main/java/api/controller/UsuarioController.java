package api.controller;

import api.arq.rest.CRUDController;
import api.controller.util.ApiInternalError;
import api.service.UsuarioService;
import api.validator.UsuarioValidator;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import api.repository.usuario.UsuarioRepository;
import api.model.Usuario;
import api.arq.seguranca.jwt.JwtService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController extends CRUDController<Usuario> {
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private UsuarioValidator usuarioValidator;
}

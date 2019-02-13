package api.controller;

import api.seguranca.jwt.JwtService;
import api.controller.dto.ParametrosLogin;
import api.model.Usuario;
import api.repository.usuario.UsuarioRepository;
import api.validator.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/login")
class LoginController{
    @Autowired
    LoginValidator loginValidator;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    JwtService jwtService;

    @InitBinder("parametrosLogin")
    public void initUsuarioBinder(WebDataBinder dataBinder) {
        dataBinder.addValidators(loginValidator);
    }

@PostMapping("/")
    public Object logar(@RequestBody @Valid ParametrosLogin parametrosLogin) {
    Optional<Usuario> optionalUsuario = usuarioRepository.findByLogin(parametrosLogin.getLogin());
    Usuario usuario = optionalUsuario.get();
    usuario.setToken(jwtService.toToken(usuario));
    return ResponseEntity.ok(usuario);
    }

}

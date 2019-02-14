package api.validator.helper;

import api.repository.usuario.UsuarioRepository;;
import api.controller.dto.ParametrosLogin;
import api.model.Usuario;
import api.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class UsuarioValidatorHelper{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Boolean usuarioAutenticado(ParametrosLogin parametrosLogin) {
        Optional<Usuario> optionalUsuario  = usuarioRepository.findByLogin(parametrosLogin.getLogin());
        if(optionalUsuario.isPresent() && passwordEncoder.matches(parametrosLogin.getSenha(), optionalUsuario.get().getSenha())){
            return true;
        }else{
            return false;
        }
    }

    public Boolean existeUsuarioInformado(Usuario entidade){
        Optional<Usuario> usuarioCadastrado = usuarioRepository.findByLogin(entidade.getLogin());
        return usuarioCadastrado.isPresent() && entidade.getId() != usuarioCadastrado.get().getId();
    }

}

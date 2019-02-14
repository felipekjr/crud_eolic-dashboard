package api.arq.util;
import api.model.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AutenticacaoUtil {
    public AutenticacaoUtil() {
        super();
    }

    public Optional<Usuario> getUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return Optional.of((Usuario) authentication.getPrincipal());
        } else {
            return Optional.empty();
        }
    }
}

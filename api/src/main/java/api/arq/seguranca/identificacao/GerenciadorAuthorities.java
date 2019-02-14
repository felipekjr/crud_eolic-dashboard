package api.arq.seguranca.identificacao;

import api.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class GerenciadorAuthorities {
    public Collection<GrantedAuthority> getAuthorities(Usuario usuario){
        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        return authorities;
    }
}


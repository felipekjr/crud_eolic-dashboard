package api.service;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import api.dao.UsuarioDao;
import api.exception.ResponseMessageObject;
import api.model.Usuario;
import api.model.UsuarioToken;

@Repository
public class ImplementUserDetailsService implements UserDetailsService {	
	@Autowired
    UsuarioDao usuarioDao;	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {		
		Usuario usuario = usuarioDao.findByLogin(login);		
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuário não existe");
		}else {
			return usuario;
		}
		
	}
	
}

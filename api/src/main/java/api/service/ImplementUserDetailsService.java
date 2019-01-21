package api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import api.dao.UsuarioDao;
import api.model.Usuario;

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

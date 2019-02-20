package api.service;

import api.arq.rest.CRUDService;
import api.model.Usuario;
import api.repository.usuario.UsuarioRepository;
import api.arq.rest.CRUDService;
import api.model.Usuario;
import api.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UsuarioService extends CRUDService<Usuario>{
    @Autowired
    private UsuarioRepository usuarioRepository;

   public void executarAntesDeSalvar(Usuario entidade) {

    }
   public void executarAposSalvar(Usuario entidade){}

   public void executarAntesDeRemover(Usuario entidade) {}

   public void executarAposRemover(Usuario entidadePersistida){}

}

package api.repository.usuario;
import api.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioRepositoryCustomizado {
    Usuario save(Usuario usuario);
}

package api.repository.usuario;

import api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RepositoryRestResource(path = "usuarios", collectionResourceRel = "usuarios")
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long>, UsuarioRepositoryCustomizado {

	Optional<Usuario> findByLogin(String login);
	Usuario findById(Optional<Long> id);
	@Override
	Iterable<Usuario> findAll();
	@Override
	Usuario save(Usuario usuario);
}



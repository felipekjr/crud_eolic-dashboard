package api.repository.usuario;

import api.model.Usuario;
import org.hibernate.boot.jaxb.hbm.spi.EntityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
/**
 * Created by Gustavo Galvao on 23/07/2018.
 */

@Service
public class UsuarioRepositoryImpl implements UsuarioRepositoryCustomizado {

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private EntityManager entityManager;

        @Transactional
        public Usuario save(Usuario usuario){
        JpaEntityInformation<Usuario, ?> entityInformation = JpaEntityInformationSupport.getEntityInformation(Usuario.class, entityManager);
            if(entityInformation.isNew(usuario)) {
                usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
                entityManager.persist(usuario);
                return usuario;
            } else {
                return entityManager.merge(usuario);
            }
        }

}

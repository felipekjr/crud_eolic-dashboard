package api.arq.rest;
import api.arq.exception.ApiErroGeralException;
import api.arq.validator.dto.ApiErroCodigo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import java.util.*;

public abstract class CRUDService<AbstractEntity>{
        @Autowired
        private CrudRepository<AbstractEntity, Long> repository;

        public AbstractEntity buscarPorId(Long id) throws Exception{
            Optional<AbstractEntity> optionalUsuario = repository.findById(id);
            if(!optionalUsuario.isPresent()) throw new ApiErroGeralException(ApiErroCodigo.NAO_ENCONTRADO);
            return optionalUsuario.get();
        }

        public AbstractEntity salvar(AbstractEntity entidade){
            return repository.save(entidade);
        }

        public void remover(AbstractEntity entidade){
           repository.delete(entidade);
        }

        public Iterable<AbstractEntity> todos(){
            return repository.findAll();
        }

        public abstract void executarAntesDeSalvar(AbstractEntity entidade);

        public abstract void executarAposSalvar(AbstractEntity entidade);

        public abstract void executarAntesDeRemover(AbstractEntity entidade);

        public abstract void executarAposRemover(AbstractEntity entidade);
}

package api.arq.rest;
import api.arq.exception.ApiErroGeralException;
import api.arq.modelo.AbstractEntity;
import api.arq.validator.dto.ApiErroCodigo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public abstract class CRUDService<T extends AbstractEntity>{
        @Autowired(required=false)
        private CrudRepository<T, Long> repository;

        public T buscarPorId(Long id) throws Exception{
            Optional<T> optionalUsuario = repository.findById(id);
            if(!optionalUsuario.isPresent()) throw new ApiErroGeralException(ApiErroCodigo.NAO_ENCONTRADO);
            return optionalUsuario.get();
        }

        public T salvar(T entidade){
            return repository.save(entidade);
        }

        public void remover(T entidade){
           repository.delete(entidade);
        }

        public Iterable<T> todos(){
            return repository.findAll();
        }

        public abstract void executarAntesDeSalvar(T entidade);

        public abstract void executarAposSalvar(T entidade);

        public abstract void executarAntesDeRemover(T entidade);

        public abstract void executarAposRemover(T entidade);
}

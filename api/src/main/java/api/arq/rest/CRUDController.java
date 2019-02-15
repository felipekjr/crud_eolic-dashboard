package api.arq.rest;

import api.arq.exception.RequisicaoInvalidaException;
import api.arq.modelo.AbstractEntity;
import api.arq.validator.AbstractCRUDValidador;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

public abstract class CRUDController<T extends AbstractEntity>{
    @Autowired
    private CRUDService<T> service;
    @Autowired
    private AbstractCRUDValidador<T> validator;
    RequisicaoInvalidaException requisicaoInvalida = new RequisicaoInvalidaException();

    @GetMapping("/{id}")
    ResponseEntity<T> buscarPorId(@PathVariable Long id) throws  Exception {
       T entidadeEncontrada = service.buscarPorId(id);
       return ResponseEntity.ok(entidadeEncontrada);
    }

    @GetMapping("/all")
    public Iterable<T> buscarTodos() {
        return service.todos();
    }

    @PostMapping("/")
    ResponseEntity<T> salvar(@RequestBody @Valid T entidade) throws Exception {
        validator.validarAntesDeSalvar(entidade);
        requisicaoInvalida.verificar(validator.erros);
        service.executarAntesDeSalvar(entidade);
        T entidadePersistida = service.salvar(entidade);
        service.executarAposSalvar(entidade);
        return ResponseEntity.ok(entidadePersistida);
    }

    @PutMapping("/{id}")
    ResponseEntity<T> editar(@PathVariable Long id, @RequestBody @Valid T entidade) throws  Exception  {
            service.buscarPorId(id);
            entidade.setId(id);
            validator.validarAntesDeSalvar(entidade);
            requisicaoInvalida.verificar(validator.erros);
            service.executarAntesDeSalvar(entidade);
            T entidadePersistida = service.salvar(entidade);
            service.executarAposSalvar(entidade);
            return ResponseEntity.ok(entidadePersistida);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<T> remover(@PathVariable Long id) throws  Exception {
        T entidadePersistida = service.buscarPorId(id);
        validator.validarAntesDeRemover(entidadePersistida);
       requisicaoInvalida.verificar(validator.erros);
        service.executarAntesDeRemover(entidadePersistida);
        service.remover(entidadePersistida);
        service.executarAposRemover(entidadePersistida);
        return ResponseEntity.ok().build();
    }

}

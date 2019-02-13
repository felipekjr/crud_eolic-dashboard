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

abstract class CRUDController extends AbstractEntity{
    @Autowired
    private CRUDService<AbstractEntity> service;

    @Autowired
    private AbstractCRUDValidador<AbstractEntity> validator;

    @Autowired
    private RequisicaoInvalidaException requisicaoInvalida;

    @GetMapping("/{id}")
    ResponseEntity<AbstractEntity> buscarPorId(@PathVariable Long id) throws  Exception {
       AbstractEntity entidadeEncontrada = service.buscarPorId(id);
       return ResponseEntity.ok(entidadeEncontrada);
    }

    @GetMapping("/all")
    public Iterable<AbstractEntity> buscarTodos() {
        return service.todos();
    }

    @PostMapping("/")
    ResponseEntity<AbstractEntity> salvar(@RequestBody @Valid AbstractEntity entidade) throws Exception {
        validator.validarAntesDeSalvar(entidade);
        requisicaoInvalida.verificar(validator.erros);
        service.executarAntesDeSalvar(entidade);
        AbstractEntity entidadePersistida = service.salvar(entidade);
        service.executarAposSalvar(entidade);
        return ResponseEntity.ok(entidadePersistida);
    }

    @PutMapping("/{id}")
    ResponseEntity<AbstractEntity> editar(@PathVariable Long id, @RequestBody @Valid AbstractEntity entidade) throws  Exception  {
            service.buscarPorId(id);
            entidade.id = id;
            validator.validarAntesDeSalvar(entidade);
            requisicaoInvalida.verificar(validator.erros);
            service.executarAntesDeSalvar(entidade);
            AbstractEntity entidadePersistida = service.salvar(entidade);
            service.executarAposSalvar(entidade);
            return ResponseEntity.ok(entidadePersistida);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<AbstractEntity> remover(@PathVariable Long id) throws  Exception {
        AbstractEntity entidadePersistida = service.buscarPorId(id);
        validator.validarAntesDeRemover(entidadePersistida);
       requisicaoInvalida.verificar(validator.erros);
        service.executarAntesDeRemover(entidadePersistida);
        service.remover(entidadePersistida);
        service.executarAposRemover(entidadePersistida);
        return ResponseEntity.ok().build();
    }

}

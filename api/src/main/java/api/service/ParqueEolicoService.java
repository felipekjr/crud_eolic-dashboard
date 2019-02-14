package api.service;

import api.arq.exception.ApiErroGeralException;
import api.arq.rest.CRUDService;
import api.arq.validator.dto.ApiErroCodigo;
import api.model.ParqueEolico;
import api.repository.ParqueEolicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Created by Gustavo Galvao on 25/09/2018.
 */

@Service
public class ParqueEolicoService extends CRUDService<ParqueEolico>{
    @Autowired
    private ParqueEolicoRepository parqueEolicoRepository;

    @Override
    public ParqueEolico buscarPorId(Long id) throws Exception {
        Optional<ParqueEolico> optionalParque = parqueEolicoRepository.findById(id);
        if (!optionalParque.isPresent())
            throw new ApiErroGeralException(ApiErroCodigo.NAO_ENCONTRADO);
        return optionalParque.get();
    }

    public void executarAntesDeSalvar(ParqueEolico entidade) {}

    public void executarAposSalvar(ParqueEolico entidade) {}

    public void executarAntesDeRemover(ParqueEolico entidade) {}

    public void executarAposRemover(ParqueEolico entidadePersistida) {}

}

package api.service;
import api.model.Usuario;
import api.repository.AerogeradorRepository;
import api.arq.rest.CRUDService;
import api.arq.util.AutenticacaoUtil;
import api.model.Aerogerador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AerogeradorService extends CRUDService<Aerogerador> {
    @Autowired
    private AerogeradorRepository aerogeradorRepository;
    AutenticacaoUtil autenticacaoUtil = new AutenticacaoUtil();

    @Override
    public void executarAntesDeSalvar(Aerogerador entidade) {
        Optional<Usuario> usuarioAutenticado = autenticacaoUtil.getUsuarioAutenticado();
    }

    @Override
    public void executarAposSalvar(Aerogerador entidade) {
    }

    @Override
    public void executarAntesDeRemover(Aerogerador entidade) {
    }

    @Override
    public void executarAposRemover(Aerogerador entidadePersistida) {
    }
}



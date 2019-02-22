package api.validator.helper;

import api.arq.util.AutenticacaoUtil;
import api.model.Aerogerador;
import api.model.ParqueEolico;
import api.model.Usuario;
import api.repository.AerogeradorRepository;
import api.repository.ParqueEolicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AerogeradorValidatorHelper{
    @Autowired
    private AerogeradorRepository aerogeradorRepository;
    @Autowired
    private ParqueEolicoRepository parqueEolicoRepository ;

    AutenticacaoUtil autenticacaoUtil = new AutenticacaoUtil();

    public Boolean existeAerogeradorComNomeInformado(Aerogerador aerogerador) {
        Optional<Aerogerador> aerogeradorCadastrado = aerogeradorRepository.findByNome(aerogerador.getNome());
        return aerogeradorCadastrado.isPresent() && aerogerador.getId() != aerogeradorCadastrado.get().getId();
    }

    public Boolean parqueEolicoNaoExiste(Aerogerador aerogerador) {
        Optional<Usuario> usuarioAutenticado = autenticacaoUtil.getUsuarioAutenticado();
        Optional<ParqueEolico> parqueEolico = parqueEolicoRepository.findById(aerogerador.getParqueEolico().getId());
        return !parqueEolico.isPresent();
    }
}






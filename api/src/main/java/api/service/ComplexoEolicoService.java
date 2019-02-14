package api.service;

import api.arq.exception.ApiErroGeralException;
import api.arq.rest.CRUDService;
import api.arq.util.AutenticacaoUtil;
import api.arq.validator.dto.ApiErroCodigo;
import api.controller.dto.ComplexoEolicoDTO;
import api.model.ComplexoEolico;
import api.model.Usuario;
import api.repository.ComplexoEolicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComplexoEolicoService extends CRUDService<ComplexoEolico>{
    @Autowired
    private ComplexoEolicoRepository complexoEolicoRepository;
    @Autowired
    private AutenticacaoUtil autenticacaoUtil;

    @Override
    public void executarAntesDeSalvar(ComplexoEolico entidade) {
        Optional<Usuario> usuarioAutenticado = autenticacaoUtil.getUsuarioAutenticado();
    }
    @Override
    public void executarAposSalvar(ComplexoEolico entidade) {}
    @Override
    public void executarAntesDeRemover(ComplexoEolico entidade) {}
    @Override
    public void executarAposRemover(ComplexoEolico entidadePersistida) {}

    public Object buscarComplexosEolicosCompletos() throws Exception{
        ArrayList<ComplexoEolicoDTO> listComplexoEolicoDTO  = new ArrayList<ComplexoEolicoDTO>();
        List<ComplexoEolico> complexosEolicos = complexoEolicoRepository.findAll();
        if (!complexosEolicos.isEmpty()) {
            for (ComplexoEolico complexoEolico : complexosEolicos) {
                ComplexoEolicoDTO complexoEolicoDTO = new ComplexoEolicoDTO(complexoEolico.getId(), complexoEolico.getNome(), complexoEolico.getUf(), complexoEolico.getParquesEolicos());
                listComplexoEolicoDTO.add(complexoEolicoDTO);
            }
            return listComplexoEolicoDTO;
        } else {
            throw new ApiErroGeralException(ApiErroCodigo.NAO_ENCONTRADO);
        }
    }
}







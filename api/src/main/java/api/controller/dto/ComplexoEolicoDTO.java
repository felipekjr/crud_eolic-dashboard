package api.controller.dto;

import api.model.ParqueEolico;

import java.util.List;

public class ComplexoEolicoDTO {
    private long id = 0;
    private String nome = "";
    private String uf = ";";
    List<ParqueEolico> parquesEolicos = null;

    public ComplexoEolicoDTO(long id, String nome, String uf, List<ParqueEolico> parquesEolicos) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
        this.parquesEolicos = parquesEolicos;
    }
}

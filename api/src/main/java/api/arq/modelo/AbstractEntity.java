package api.arq.modelo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Gustavo Galvao on 03/08/2018.
 */

@MappedSuperclass
public class AbstractEntity {
    @Id
    @GenericGenerator(name="incrementador" , strategy="increment")
    @GeneratedValue(generator="incrementador")
    public Long id;

    public AbstractEntity() {
    }

    @NotNull
    public Boolean ativo = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}

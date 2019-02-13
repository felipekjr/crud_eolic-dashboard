package api.arq.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Gustavo Galvao on 03/08/2018.
 */

@MappedSuperclass
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_generator")
    public long id = 0;

    @NotNull
    public Boolean ativo = true;
}

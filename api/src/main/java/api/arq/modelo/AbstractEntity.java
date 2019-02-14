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
    public long id = 0;

    @NotNull
    public Boolean ativo = true;
}

package api.arq.validator;

import api.arq.modelo.AbstractEntity;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractCRUDValidador<T extends AbstractEntity> extends  AbstractValidator {
    public abstract void validarAntesDeSalvar(T entidade);
    public abstract void validarAntesDeRemover(T entidade);
}
